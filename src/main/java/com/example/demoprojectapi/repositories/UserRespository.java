package com.example.demoprojectapi.repositories;

import com.example.demoprojectapi.exceptions.AuthException;
import com.example.demoprojectapi.models.User;

public interface UserRespository {
    Integer createUser(String firsName, String lastName, String email, String password) throws AuthException;

    User getUser(String email , String password) throws AuthException;

    Integer getCountByEmail(String email);

    User findUser(Integer userId);
}
