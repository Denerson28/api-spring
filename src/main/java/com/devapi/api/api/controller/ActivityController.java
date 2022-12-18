package com.devapi.api.api.controller;


import com.devapi.api.domain.model.Activity;
import com.devapi.api.domain.model.Edition;
import com.devapi.api.domain.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityRepository repository;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Activity> Cadastrar(@RequestBody Activity activity){
        System.out.println(activity);
        repository.save(activity);
        return ResponseEntity.status(HttpStatus.CREATED).body(activity);
    }

    @GetMapping
    public List<Activity> Listar(){
        return repository.findAll();
    }
}
