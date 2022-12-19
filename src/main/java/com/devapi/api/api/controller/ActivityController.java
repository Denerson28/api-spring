package com.devapi.api.api.controller;


import com.devapi.api.domain.model.Activity;
import com.devapi.api.domain.model.Edition;
import com.devapi.api.domain.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityRepository repository;

    @PostMapping
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public ResponseEntity<Activity> Cadastrar(@RequestBody Activity activity){
        System.out.println(activity);
        repository.save(activity);
        return ResponseEntity.status(HttpStatus.CREATED).body(activity);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<Activity> Listar(){
        return repository.findAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public ResponseEntity<Activity> editar(@PathVariable("id") Long atividadeId, @RequestBody Activity atividadeInfo) {
        Activity atividade = repository.findById(atividadeId).get();

        atividade.setNome(atividadeInfo.getNome());
        atividade.setDescricao(atividadeInfo.getDescricao());
        atividade.setData(atividadeInfo.getData());
        atividade.setHorario_inicial(atividadeInfo.getHorario_inicial());
        atividade.setHorario_final(atividadeInfo.getHorario_final());

        final Activity activityAtt = repository.save(atividade);
        return ResponseEntity.ok(activityAtt);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public Map<String, Boolean> deletar(@PathVariable("id") Long atividadeId) {
        Activity atividade = repository.findById(atividadeId).get();

        repository.delete(atividade);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Atividade Deletada", Boolean.TRUE);
        return response;
    }
}
