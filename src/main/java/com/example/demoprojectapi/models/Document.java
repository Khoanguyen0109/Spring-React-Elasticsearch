package com.example.demoprojectapi.models;

import java.time.OffsetDateTime;

public class Document {
    private Integer docId;
    private Integer userId;
    private String name;
    private String content;
    private String type;
    private OffsetDateTime upload_date;
    public Document(Integer docId, Integer userId, String name, String content, String type,
            OffsetDateTime upload_date) {
        this.docId = docId;
        this.userId = userId;
        this.name = name;
        this.content = content;
        this.type = type;
        this.upload_date = upload_date;
    }
    public Integer getDocId() {
        return docId;
    }
    public void setDocId(Integer docId) {
        this.docId = docId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public OffsetDateTime getUpload_date() {
        return upload_date;
    }
    public void setUpload_date(OffsetDateTime upload_date) {
        this.upload_date = upload_date;
    } 
}
