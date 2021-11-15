package com.example.demoprojectapi.models;


import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "document")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "documents")
@Setting(settingPath = "static/es-settings.json")
public class
Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field(type = FieldType.Long)
    private long id;

    @Field(type = FieldType.Text)
    @Column(length = 512, nullable = false, unique = true)
    private String name;

    @Field(type = FieldType.Long)
    private long size;

    @Field(type = FieldType.Text)
    private String type;

    @Field(type = FieldType.Date)
    @Column(name = "upload_date")
    private Date uploadTime;

    @Field(type = FieldType.Binary)
    private byte[] contnet;

    public Document() {
    }

    public Document(long id, String name, long size, String type, Date uploadTime, byte[] contnet) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.type = type;
        this.uploadTime = uploadTime;
        this.contnet = contnet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public byte[] getContnet() {
        return contnet;
    }

    public void setContnet(byte[] contnet) {
        this.contnet = contnet;
    }
}
