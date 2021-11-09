package com.example.demoprojectapi.controllers;

import java.time.OffsetDateTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.demoprojectapi.models.Document;
import com.example.demoprojectapi.services.DocumentServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documents")
public class DocumentResource {
    
    @Autowired
    DocumentServices documentServices;

    @GetMapping("")
    public String getAllDocuments(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        // List<Category> categories = categoryService.fetchAllCategories(userId);
        // return new ResponseEntity<>(categories, HttpStatus.OK);4
        return "Authenticated! UserId: " + userId;
    }

    @PostMapping("")
    public ResponseEntity<Document> addDocument(HttpServletRequest request, @RequestBody Map<String, Object> documentMap){
        int userId = (Integer) request.getAttribute("userId");
        String name = (String) documentMap.get("name");
        String content = (String) documentMap.get("content");
        String type = (String) documentMap.get("type");
        // OffsetDateTime upload_date = (OffsetDateTime) documentMap.get("upload_date");
        Document document = documentServices.addDocument(userId, name, content, type);
        return new ResponseEntity<>(document, HttpStatus.CREATED);
    }
}
