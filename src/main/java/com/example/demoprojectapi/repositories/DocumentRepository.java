package com.example.demoprojectapi.repositories;

import java.util.List;

import com.example.demoprojectapi.exceptions.EtBadRequestException;
import com.example.demoprojectapi.exceptions.EtResourceNotFoundException;
import com.example.demoprojectapi.models.Document;

public interface DocumentRepository {
    List<Document> findAll(Integer userId) throws EtResourceNotFoundException;
    Document findById(Integer userid, Integer docId) throws EtResourceNotFoundException;
    Integer create(Integer userId, String name, String content, String type) throws EtBadRequestException;
    void update(Integer userId, Integer docId, Document document) throws EtBadRequestException;
    void removeById(Integer userId, Integer docId);
}
