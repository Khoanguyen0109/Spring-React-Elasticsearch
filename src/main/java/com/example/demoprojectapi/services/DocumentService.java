package com.example.demoprojectapi.services;

import java.util.List;

import com.example.demoprojectapi.exceptions.BadRequestException;
import com.example.demoprojectapi.exceptions.ResourceNotFoundException;
import com.example.demoprojectapi.models.Document;

public interface DocumentService {
    List<Document> fetchAllDocuments(Integer userId);
    Document fetchDocumentById(Integer userId, Integer documentId) throws ResourceNotFoundException;
    Document addDocument(Integer userId, String name, String content, String type) throws BadRequestException;
    void updateDocument(Integer userId, Integer documentId, Document document) throws BadRequestException;
    void removeDocument(Integer userId, Integer documentId) throws ResourceNotFoundException;
}
