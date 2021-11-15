package com.example.demoprojectapi.elasticsearchRepositories;

import com.example.demoprojectapi.models.Document;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ESDocumentRepository extends ElasticsearchRepository<Document, Long> {
    void deleteById(Long id);

}
