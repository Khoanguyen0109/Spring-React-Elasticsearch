package com.example.demoprojectapi.services;

import com.example.demoprojectapi.exceptions.EtAuthException;
import com.example.demoprojectapi.models.User;
import com.example.demoprojectapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl  implements  UserServices{

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        if (email != null) email = email.toLowerCase();
        return userRepository.findUserByEmailAndPassword(email, password);


    }
    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        if(email !=null ) email = email.toLowerCase();
        if(!pattern.matcher(email).matches()){
            throw new EtAuthException("Invalid email format");
        }
        Integer count = userRepository.countByEmail(email);
        if(count> 0) {
            System.out.println("sdsds"+ count);

            throw new EtAuthException("Email have already exits");
        }
        User user = userRepository.save( new User(firstName, lastName, email, password));
        return user;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepository.findByUserName(username);

        if(null==user) {
            throw new UsernameNotFoundException("User Not Found with userName "+username);
        }
        return user;
    }
}
