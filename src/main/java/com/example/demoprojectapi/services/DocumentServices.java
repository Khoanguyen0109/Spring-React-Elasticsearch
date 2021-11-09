package com.example.demoprojectapi.services;

import java.util.List;

import com.example.demoprojectapi.exceptions.EtAuthException;
import com.example.demoprojectapi.exceptions.EtBadRequestException;
import com.example.demoprojectapi.exceptions.EtResourceNotFoundException;
import com.example.demoprojectapi.models.Document;




public interface DocumentServices {
    List<Document> fetchAllDocuments(Integer userId);
    Document fetchDocumentById(Integer userId, Integer docId) throws EtResourceNotFoundException;
    Document addDocument(Integer userId, String name, String content, String type) throws EtBadRequestException;
    void updateDocument(Integer userId, Integer docId, Document document) throws EtBadRequestException;
    void DeteleDocument(Integer userId, Integer docId) throws EtResourceNotFoundException;
}
