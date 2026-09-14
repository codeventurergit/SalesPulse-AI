package com.example.salespulse.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "salesforce_leads")
public class LeadDocument {
    
    @Id
    private String id;
    private String companyName;
    private String currentTechStack;
    private String painPointDescription;
    private Double estimatedDealValue;
    private String aiStrategySummary; // Stores the finalized RAG insights from Bedrock

    // Standard Enterprise Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getCurrentTechStack() { return currentTechStack; }
    public void setCurrentTechStack(String currentTechStack) { this.currentTechStack = currentTechStack; }
    public String getPainPointDescription() { return painPointDescription; }
    public void setPainPointDescription(String painPointDescription) { this.painPointDescription = painPointDescription; }
    public Double getEstimatedDealValue() { return estimatedDealValue; }
    public void setEstimatedDealValue(Double estimatedDealValue) { this.estimatedDealValue = estimatedDealValue; }
    public String getAiStrategySummary() { return aiStrategySummary; }
    public void setAiStrategySummary(String aiStrategySummary) { this.aiStrategySummary = aiStrategySummary; }
}
