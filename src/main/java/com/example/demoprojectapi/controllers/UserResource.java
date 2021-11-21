package com.example.demoprojectapi.controllers;

import com.example.demoprojectapi.config.JWTHelper;
import com.example.demoprojectapi.response.UserInfo;
import com.example.demoprojectapi.ultis.Constant;
import com.example.demoprojectapi.models.User;
import com.example.demoprojectapi.services.UserServices;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;


import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserResource {

    @Autowired
    UserServices userServices;

    @Autowired
    JWTHelper jWTTokenHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> resgisterUser(@RequestBody Map<String, Object> userMap) {
        try {
            String firstName = (String) userMap.get("firstName");
            String lastName = (String) userMap.get("lastName");
            String email = (String) userMap.get("email");
            String password = (String) userMap.get("password");
            User user = userServices.registerUser(firstName, lastName, email, password);
            Map<String, String> map = new HashMap<>();
            map.put("message", "register successfully");
            return new ResponseEntity<>( map, HttpStatus.OK);
        }catch (Exception e){
            Map<String, String> map = new HashMap<>();
            map.put("message", "register unsuccessfully");
            return new ResponseEntity<>( map, HttpStatus.BAD_REQUEST);
        }

    }
    ;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, Object> userMap) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                email ,password ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user=(User)authentication.getPrincipal();
        String jwtToken= jWTTokenHelper.generateToken(user.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("user" , user);
        map.put("token" , jwtToken);
        return new ResponseEntity<>( HttpStatus.OK);

    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(Principal user){
        User userObj=(User) userServices.loadUserByUsername(user.getName());

        UserInfo userInfo=new UserInfo();
        userInfo.setFirstName(userObj.getFirstName());
        userInfo.setLastName(userObj.getLastName());
        return ResponseEntity.ok(userInfo);



    }
}


