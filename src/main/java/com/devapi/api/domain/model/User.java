package com.devapi.api.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String nome;

    private String email;

    private String afiliacao;

    public User(User user) {
        this.id = user.id;
        this.login = user.login;
        this.nome = user.nome;
        this.email = user.email;
        this.afiliacao = user.afiliacao;
    }

}
