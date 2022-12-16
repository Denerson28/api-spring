package com.devapi.api.api.controller;

import com.devapi.api.domain.model.User;
import com.devapi.api.domain.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    public ResponseEntity<User> Cadastrar(@RequestBody User user){

        System.out.println(user);
        repository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    public List<User> Listar(){
        return repository.findAll();
    }
}