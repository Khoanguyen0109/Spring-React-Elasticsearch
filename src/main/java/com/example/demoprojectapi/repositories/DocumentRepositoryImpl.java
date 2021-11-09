package com.example.demoprojectapi.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.example.demoprojectapi.exceptions.EtBadRequestException;
import com.example.demoprojectapi.exceptions.EtResourceNotFoundException;
import com.example.demoprojectapi.models.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository{

    private static final String SQL_FIND_BY_ID = "Select id, user_id, name, content, type, upload_date From documents where user_id = ? and id = ?";
    private static final String SQL_CREATE = "Insert into documents (id, user_id, name, content, type, upload_date) values(nextval('documents_seq'), ?, ?, ?, ?, ?)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Document> findAll(Integer userId) throws EtResourceNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Document findById(Integer userid, Integer docId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userid, docId}, documentRowMapper);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Document not found");
        }
    }

    @Override
    public Integer create(Integer userId, String name, String content, String type) throws EtBadRequestException {
        try {
            OffsetDateTime upload_date = OffsetDateTime.now();
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setString(2, name);
                ps.setString(3, content);
                ps.setString(4, type);
                ps.setObject(5, upload_date);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("id");
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer userId, Integer docId, Document document) throws EtBadRequestException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeById(Integer userId, Integer docId) {
        // TODO Auto-generated method stub
        
    }

    private RowMapper<Document> documentRowMapper =((rs, rowNum) -> {
        return new Document(rs.getInt("docId"),
                rs.getInt("userId"),
                rs.getString("name"),
                rs.getString("content"),
                rs.getString("type"),
                (OffsetDateTime) rs.getObject("upload_date")
                );
    });

}
