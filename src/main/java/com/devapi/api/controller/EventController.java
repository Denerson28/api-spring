package com.devapi.api.controller;

import com.devapi.api.domain.model.Event;
import com.devapi.api.domain.model.User;
import com.devapi.api.domain.repository.EventRepository;
import com.devapi.api.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository repository;

    @PostMapping
    public ResponseEntity<Event> Cadastrar(@RequestBody Event event){

        System.out.println(event);
        repository.save(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

    @GetMapping
    public List<Event> Listar(){
        return repository.findAll();
    }
}
