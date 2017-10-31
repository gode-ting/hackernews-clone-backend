/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daef.controllers;

import com.daef.models.ApplicationUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.daef.repositories.ApplicationUserRepository;
import com.daef.utils.AuthUtil;
import java.util.List;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author emilgras
 */

@RestController
@RequestMapping("/users")
public class UserController {
    
    private ApplicationUserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(ApplicationUserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    @GetMapping()
    public String hello() {
        return "hello";
    }
    
    
    @PostMapping("/signup")
    public ResponseEntity<JSONObject> signUp(@RequestBody ApplicationUser user) {

        // 1. validate credentials
        if (!AuthUtil.credentialsAreValid(user)) {
            return new ResponseEntity<>(AuthUtil.getErrorMessage(user), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        
        // 2. check if user already excists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            JSONObject response = new JSONObject();
            response.put("message", "Username is already taken");
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        
        // 3. hash password and update user
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        
        // 4. save user
        userRepository.save(user);

        JSONObject response = new JSONObject();
        response.put("message", "New user created");
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }
    
}
