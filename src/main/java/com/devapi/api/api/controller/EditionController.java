package com.devapi.api.api.controller;


import com.devapi.api.domain.model.Edition;
import com.devapi.api.domain.model.Event;
import com.devapi.api.domain.repository.EditionRepository;
import com.devapi.api.domain.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editions")
public class EditionController {

    @Autowired
    private EditionRepository repository;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Edition> Cadastrar(@RequestBody Edition edition){
        System.out.println(edition);
        repository.save(edition);
        return ResponseEntity.status(HttpStatus.CREATED).body(edition);
    }

    @GetMapping
    public List<Edition> Listar(){
        return repository.findAll();
    }
}
