package com.example.demoprojectapi.repositories;

import com.example.demoprojectapi.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document ,Long> {
    List<Document> findByName(String name);

//    @Query("SELECT id, size , name , type , upload_date from document order by upload_date DESC ")
    List<Document> findAll();

    void deleteById(Long id);

}
