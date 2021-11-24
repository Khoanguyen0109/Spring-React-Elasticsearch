package com.example.demoprojectapi.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import com.example.demoprojectapi.exceptions.BadRequestException;
import com.example.demoprojectapi.exceptions.ResourceNotFoundException;
import com.example.demoprojectapi.models.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    private static final String SQL_FIND_ALL = "Select id, user_id,name,content,type,upload_date from documents where user_id=?";
    private static final String SQL_FIND_BY_ID = "Select id, user_id,name,content,type,upload_date from documents where user_id=? and id=?";
    private static final String SQL_CREATE = "Insert into documents(id, user_id,name,content,type) values(nextval('documents_seq'),?,?,?,?)";
    private static final String SQL_UPDATE = "Update documents set name=?,content=?,type=? where user_id=? and id=?";
    private static final String SQL_Delete = "Delete from documents where user_id=? and id=?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Document> findAll(Integer userId) {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{userId}, documentRowMapper);
    }

    @Override
    public Document findById(Integer userId, Integer documentId) throws ResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId,documentId}, documentRowMapper);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Document not found");
        }
    }

    @Override
    public Integer create(Integer userId, String name, String content, String type) throws BadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setString(2, name);
                ps.setString(3, content);
                ps.setString(4, type);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("id"); 
        } catch (Exception e) {
            throw new BadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer userId, Integer documentId, Document document) {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{document.getName(), document.getContent(), document.getType(), userId, documentId});
        } catch (Exception e) {
            throw new BadRequestException("Invalid request");
        }
        
    }

    @Override
    public void removeById(Integer userId, Integer documentId) throws ResourceNotFoundException {
        int count = jdbcTemplate.update(SQL_Delete, new Object[]{userId, documentId});
        if (count == 0)
            throw new ResourceNotFoundException("Document not found");
    }

    private RowMapper<Document> documentRowMapper = ((rs, rowNum) -> {
        return new Document(rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("name"),
                            rs.getString("content"),
                            rs.getString("type"),
                            rs.getTimestamp("upload_date"));
    });
    
}
