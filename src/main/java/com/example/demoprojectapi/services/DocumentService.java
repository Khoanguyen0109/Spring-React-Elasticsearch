package com.example.demoprojectapi.services;

import com.example.demoprojectapi.elasticsearchRepositories.ESDocumentRepository;
import com.example.demoprojectapi.models.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    private final ESDocumentRepository esDocumentRepository;

    @Autowired
    public DocumentService(ESDocumentRepository esDocumentRepository){
        this.esDocumentRepository = esDocumentRepository;
    }

    public void save (final Document document){
        esDocumentRepository.save(document);
    }
    public void deleteById (Long id){
        esDocumentRepository.deleteById(id);
    }


}
