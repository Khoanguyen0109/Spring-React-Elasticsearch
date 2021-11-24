package com.example.demoprojectapi.repositories;

import java.util.List;

import com.example.demoprojectapi.exceptions.BadRequestException;
import com.example.demoprojectapi.exceptions.ResourceNotFoundException;
import com.example.demoprojectapi.models.Document;

public interface DocumentRepository {
    List<Document> findAll(Integer userId);
    Document findById(Integer userId, Integer documentId) throws ResourceNotFoundException;
    Integer create(Integer userId, String name, String content, String type) throws BadRequestException;
    void update(Integer userId, Integer documentId, Document document);
    void removeById(Integer userId, Integer documentId) throws ResourceNotFoundException;
}
