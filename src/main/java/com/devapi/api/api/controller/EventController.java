package com.devapi.api.api.controller;

import com.devapi.api.domain.model.Edition;
import com.devapi.api.domain.model.Event;
import com.devapi.api.domain.model.User;
import com.devapi.api.domain.repository.EventRepository;
import com.devapi.api.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository repository;

    @PostMapping
    public ResponseEntity<Event> Cadastrar(@RequestBody Event event){
        repository.save(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

    @GetMapping
    public List<Event> Listar(){
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> editar(@PathVariable("id") Long eventId, @RequestBody Event eventInfo) {
        Event event = repository.findById(eventId).get();

        event.setDescricao(eventInfo.getDescricao());
        event.setNome(eventInfo.getNome());
        event.setSigla(eventInfo.getSigla());


        final Event eventAtt = repository.save(event);
        return ResponseEntity.ok(eventAtt);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deletar(@PathVariable("id") Long eventId) {
        Event event = repository.findById(eventId).get();

        repository.delete(event);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Edição Deletada", Boolean.TRUE);
        return response;
    }
}
