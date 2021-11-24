package com.example.demoprojectapi.services;

import com.example.demoprojectapi.exceptions.AuthException;
import com.example.demoprojectapi.models.User;

public interface UserServices {
    User validateUser (String email, String password)throws AuthException;

    User registerUser(String firstName, String lastName, String email , String password) throws AuthException;
}
