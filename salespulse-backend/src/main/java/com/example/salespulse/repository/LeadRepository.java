package com.example.salespulse.repository;

import com.example.salespulse.model.LeadDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends MongoRepository<LeadDocument, String> {
    // Spring Data automatically implements standard NoSQL CRUD infrastructure hooks here
}
