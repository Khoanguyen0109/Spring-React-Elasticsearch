package com.example.demoprojectapi.repositories;

import com.example.demoprojectapi.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document ,Long> {
}
