package com.example.demoprojectapi.services;

import com.example.demoprojectapi.exceptions.EtAuthException;
import com.example.demoprojectapi.models.User;
import com.example.demoprojectapi.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl  implements  UserServices{

    @Autowired
    UserRespository userRespository;

    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        if (email!=null) email = email.toLowerCase();
        return userRespository.getUser(email, password);
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {
        System.out.println("service" + firstName);
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        if(email !=null ) email = email.toLowerCase();
        if(!pattern.matcher(email).matches()){
            throw new EtAuthException("Invalid email format");
        }
        Integer count = userRespository.getCountByEmail(email);
        if(count> 0) throw new EtAuthException("Email have already exits");
        Integer userId = userRespository.createUser(firstName, lastName, email, password);
        System.out.println(userId);
        return userRespository.findUser(userId);
    }
}
