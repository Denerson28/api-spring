package com.devapi.api.api.controller;

import com.devapi.api.config.PasswordConfig;
import com.devapi.api.domain.model.User;
import com.devapi.api.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping(value = "/create")
    public ResponseEntity<User> Create(@RequestBody User user){
        // user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        repository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/read")
    public List<User> Read(){
        return repository.findAll();
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<User> Update(@PathVariable(value = "id") long id, @RequestBody User newUser) {
        Optional<User> oldUser = repository.findById(id);
        if(oldUser.isPresent()) {
            User user = oldUser.get();
            user.setName(newUser.getName());
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setAffiliation(newUser.getAffiliation());
            user.setEmail(newUser.getEmail());
            repository.save(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
        Optional<User> user = repository.findById(id);
        if(user.isPresent()) {
            repository.delete(user.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}