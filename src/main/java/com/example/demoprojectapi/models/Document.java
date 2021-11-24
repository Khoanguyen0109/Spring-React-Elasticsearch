package com.example.demoprojectapi.models;

import java.sql.Timestamp;

public class Document {
    private int id;
    private int user_id;
    private String name;
    private String content;
    private String type;
    private Timestamp upload_date;
    public Document(int id, int user_id, String name, String content, String type, Timestamp upload_date) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.content = content;
        this.type = type;
        this.upload_date = upload_date;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
    public Timestamp getUpload_date() {
        return upload_date;
    }
    public void setUpload_date(Timestamp upload_date) {
        this.upload_date = upload_date;
    }
    
}
