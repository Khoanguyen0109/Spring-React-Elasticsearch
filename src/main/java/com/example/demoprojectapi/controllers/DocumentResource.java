package com.example.demoprojectapi.controllers;

import com.example.demoprojectapi.models.Document;
import com.example.demoprojectapi.repositories.DocumentRepository;
import com.example.demoprojectapi.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/documents")
public class DocumentResource {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentService documentService;

    @GetMapping("")
    public ResponseEntity<Object> getAllDocuments(HttpServletRequest request){
        List<Document> listDoc = documentRepository.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put("documents", listDoc);
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @PostMapping("upload")
    public ResponseEntity<Object> uploadFile ( HttpServletRequest request ,@RequestParam("document") MultipartFile file) throws IOException {
        Integer userId = (Integer) request.getAttribute("userId");
        System.out.println((userId));
        String fileName = StringUtils.cleanPath( file.getOriginalFilename());
        String mimeType = file.getContentType();
        String[] allowMineType = new String[]{ "application/msword",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                "application/pdf",
                "application/vnd.oasis.opendocument.text",
                "application/vnd.ms-works",
                "application/x-msworks-wp",
                "zz-application/zz-winassoc-wps"};

        boolean allow = Arrays.stream(allowMineType)
                .anyMatch(x -> x.equals(mimeType));
        if(allow == false){
            Map<String, String> map = new HashMap<>();
            map.put("message", "file type not allow");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }

        if(documentRepository.findByName(fileName).size() >0){
            Map<String, String> map = new HashMap<>();
            map.put("message", "name already exits");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);

        }
        Document doc = new Document();
        doc.setName(fileName);
        doc.setContnet(file.getBytes());
        doc.setSize(file.getSize());
        doc.setType(mimeType);
        doc.setUploadTime(new Date());
        documentRepository.save(doc);
        documentService.save(doc);
//        esDocumentRepository.save(doc);
        Map<String, String> map = new HashMap<>();
        map.put("message", "upload successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @DeleteMapping( "{id}")
    public ResponseEntity<Object> deleteFile(@PathVariable Long id){
        System.out.println(id);
        Map<String, Object> map = new HashMap<>();
        try {
            documentRepository.deleteById(id);
            documentService.deleteById(id);
            map.put("id" , id);
            map.put("message", "delete successfully");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);

        }

    }
}
