package com.example.demoprojectapi.services;

import java.util.List;

import com.example.demoprojectapi.exceptions.EtBadRequestException;
import com.example.demoprojectapi.exceptions.EtResourceNotFoundException;
import com.example.demoprojectapi.models.Document;
import com.example.demoprojectapi.repositories.DocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DocumentServicesImpl implements DocumentServices{

    @Autowired
    DocumentRepository documentRepository;

    @Override
    public List<Document> fetchAllDocuments(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Document fetchDocumentById(Integer userId, Integer docId) throws EtResourceNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Document addDocument(Integer userId, String name, String content, String type)
            throws EtBadRequestException {
        int docId = documentRepository.create(userId, name, content, type);
        return documentRepository.findById(userId, docId);
    }

    @Override
    public void updateDocument(Integer userId, Integer docId, Document document) throws EtBadRequestException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void DeteleDocument(Integer userId, Integer docId) throws EtResourceNotFoundException {
        // TODO Auto-generated method stub
        
    }
    
}
