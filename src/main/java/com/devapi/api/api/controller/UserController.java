package com.devapi.api.api.controller;

import com.devapi.api.domain.model.User;
import com.devapi.api.domain.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping(value = "/create")
    public ResponseEntity Create(@RequestBody User user){
        repository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}