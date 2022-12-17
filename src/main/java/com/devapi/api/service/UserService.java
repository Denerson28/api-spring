package com.devapi.api.service;

import com.devapi.api.domain.model.User;
import com.devapi.api.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private UserRepository repository;

    public void UsuarioService(UserRepository usuarioRepository) {
        this.repository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(repository.findByUsername(username));
        if(user.isPresent()) {
            return (UserDetails) repository.findByUsername(username);
        }
        else {
            throw new UsernameNotFoundException(String.format("Usuário %s não encontrado!", username));
        }
    }
}