package com.example.demoprojectapi.services;

import com.example.demoprojectapi.exceptions.EtAuthException;
import com.example.demoprojectapi.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices extends UserDetailsService {
    User validateUser (String email, String password)throws EtAuthException;

    User registerUser(String firstName, String lastName, String email , String password) throws EtAuthException;


}
