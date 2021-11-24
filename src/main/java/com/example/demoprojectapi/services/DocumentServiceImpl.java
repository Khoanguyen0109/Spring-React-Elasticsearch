package com.example.demoprojectapi.services;

import java.util.List;

import com.example.demoprojectapi.exceptions.BadRequestException;
import com.example.demoprojectapi.exceptions.ResourceNotFoundException;
import com.example.demoprojectapi.models.Document;
import com.example.demoprojectapi.repositories.DocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    @Override
    public List<Document> fetchAllDocuments(Integer userId) {
        return documentRepository.findAll(userId);
    }

    @Override
    public Document fetchDocumentById(Integer userId, Integer documentId) throws ResourceNotFoundException {
        return documentRepository.findById(userId, documentId);
    }

    @Override
    public Document addDocument(Integer userId, String name, String content, String type) throws BadRequestException {
        int documentId = documentRepository.create(userId, name, content, type);
        return documentRepository.findById(userId, documentId);
    }

    @Override
    public void updateDocument(Integer userId, Integer documentId, Document document) throws BadRequestException {
        documentRepository.update(userId, documentId, document);        
    }

    @Override
    public void removeDocument(Integer userId, Integer documentId) throws ResourceNotFoundException {
        documentRepository.removeById(userId, documentId);    
    }
    
}
