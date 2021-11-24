package com.example.demoprojectapi.resource_controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.demoprojectapi.models.Document;
import com.example.demoprojectapi.services.DocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/{userId}/documents")
public class DocumentResource {
    
    @Autowired
    DocumentService documentService;

    @GetMapping("")
    public ResponseEntity<List<Document>> getAllDocuments(HttpServletRequest request, @PathVariable("userId") Integer userId){
        List<Document> documents = documentService.fetchAllDocuments(userId);
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    
    @GetMapping("{documentId}")
    public ResponseEntity<Document> getDocumentById(HttpServletRequest request, @PathVariable("userId") Integer userId, @PathVariable("documentId") Integer documentId){
        Document document = documentService.fetchDocumentById(userId, documentId);
        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Document> addDocument(HttpServletRequest request, @PathVariable("userId") Integer userId, @RequestBody Map<String, Object> documentMap){
        String name = String.valueOf(documentMap.get("name").toString());
        String content = String.valueOf(documentMap.get("content").toString());
        String type = String.valueOf(documentMap.get("type").toString());
        Document document = documentService.addDocument(userId, name, content, type);
        return new ResponseEntity<>(document, HttpStatus.CREATED);
    }

    @PutMapping("/{documentId}")
    public ResponseEntity<Map<String, Boolean>> updateDocument(HttpServletRequest request, @PathVariable("userId") Integer userId, @PathVariable("documentId") Integer documentId, @RequestBody Document document){
        documentService.updateDocument(userId, documentId, document);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{documentId}")
    public ResponseEntity<Map<String, Boolean>> deleteDocument(HttpServletRequest request, @PathVariable("userId") Integer userId, @PathVariable("documentId") Integer documentId, @RequestBody Document document){
        documentService.removeDocument(userId, documentId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
