package com.devapi.api.api.controller;


import com.devapi.api.domain.model.Activity;
import com.devapi.api.domain.model.Edition;
import com.devapi.api.domain.model.Event;
import com.devapi.api.domain.repository.EditionRepository;
import com.devapi.api.domain.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Edition> Listar(){
        return repository.findAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ORGANIZER')")
    public ResponseEntity<Edition> editar(@PathVariable("id") Long editionId, @RequestBody Edition editionInfo) {
        Edition edition = repository.findById(editionId).get();

        edition.setNumero(editionInfo.getNumero());
        edition.setAno(editionInfo.getAno());
        edition.setData_inicial(editionInfo.getData_inicial());
        edition.setData_final(editionInfo.getData_final());
        edition.setCidade(editionInfo.getCidade());

        final Edition editionAtt = repository.save(edition);
        return ResponseEntity.ok(editionAtt);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Map<String, Boolean> deletar(@PathVariable("id") Long editionId) {
        Edition edition = repository.findById(editionId).get();

        repository.delete(edition);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Edição Deletada", Boolean.TRUE);
        return response;
    }
}
