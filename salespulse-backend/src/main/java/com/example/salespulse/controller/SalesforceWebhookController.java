package com.example.salespulse.controller;

import com.example.salespulse.model.LeadDocument;
import com.example.salespulse.repository.LeadRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/salesforce")
@CrossOrigin(origins = "*") // Vital for connecting your Angular Dashboard seamlessly later
public class SalesforceWebhookController {

    private final LeadRepository leadRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    // Relative path pointing to our Codespace streaming staging directory
    private static final String STAGING_DIR = "../staging-data/";

    public SalesforceWebhookController(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    @PostMapping("/webhook")
    public ResponseEntity<LeadDocument> receiveApexCallout(@RequestBody LeadDocument rawLead) {
        System.out.println("Inbound Adapter: Captured Salesforce payload for " + rawLead.getCompanyName());
        
        try {
            // 1. Instantly persist transactional data state to MongoDB Cloud
            LeadDocument savedDoc = leadRepository.save(rawLead);
            
            // 2. Guarantee the staging stream directory path exists
            File directory = new File(STAGING_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 3. Serialize to an isolated event file for the PySpark Streaming computation
            String eventFile = "event_" + UUID.randomUUID() + ".json";
            File outputFile = new File(directory, eventFile);
            objectMapper.writeValue(outputFile, savedDoc);
            
            System.out.println("⚡ Event piped to PySpark staging framework: " + eventFile);
            return ResponseEntity.ok(savedDoc);
            
        } catch (Exception e) {
            System.err.println("❌ Architectural Ingestion Failure: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
