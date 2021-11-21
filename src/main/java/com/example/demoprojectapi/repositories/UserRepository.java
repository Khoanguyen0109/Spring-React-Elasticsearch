package com.example.demoprojectapi.repositories;

import com.example.demoprojectapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
    User findUserById(User id);
    User findByUserName(String userName);
    User findUserByEmail(String email);
    User findUserByEmailAndPassword(String email , String password);
    Integer countByEmail(String email);
}
