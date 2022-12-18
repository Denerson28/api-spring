package com.devapi.api.api.controller;

import com.devapi.api.domain.model.Activity;
import com.devapi.api.domain.model.Space;
import com.devapi.api.domain.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spaces")
public class SpaceController {
    @Autowired
    private SpaceRepository repository;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Space> Cadastrar(@RequestBody Space space){
        System.out.println(space);
        repository.save(space);
        return ResponseEntity.status(HttpStatus.CREATED).body(space);
    }

    @GetMapping
    public List<Space> Listar(){
        return repository.findAll();
    }
}
