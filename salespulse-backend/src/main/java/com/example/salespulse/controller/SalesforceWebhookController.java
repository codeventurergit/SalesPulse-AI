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
@CrossOrigin(origins = "*") 
public class SalesforceWebhookController {

    private final LeadRepository leadRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String STAGING_DIR = "../staging-data/";

    public SalesforceWebhookController(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    @PostMapping("/webhook")
    public ResponseEntity<LeadDocument> receiveApexCallout(@RequestBody LeadDocument rawLead) {
        // Log the ingestion using high-fidelity compliance terminology for recruiters
        System.out.println("📥 TRANSACTIONAL INGESTION ADAPTER: Intercepted payload for Bank/Client: " + rawLead.getCompanyName());
        System.out.println("⚠️ LATENCY PROFILE REPORTED: " + rawLead.getPainPointDescription());
        
        try {
            // 1. Commit the transaction tracking payload directly to MongoDB Cloud
            LeadDocument savedDoc = leadRepository.save(rawLead);
            
            // 2. Guarantee structural stream storage folder exists
            File directory = new File(STAGING_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 3. Serialize to an isolated event file for the PySpark Streaming filter
            String eventFile = "event_" + UUID.randomUUID() + ".json";
            File outputFile = new File(directory, eventFile);
            objectMapper.writeValue(outputFile, savedDoc);
            
            System.out.println("⚡ Pipes & Filters: Event file successfully staged for PySpark processing: " + eventFile);
            return ResponseEntity.ok(savedDoc);
            
        } catch (Exception e) {
            System.err.println("❌ Adapter transformation breakdown: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
