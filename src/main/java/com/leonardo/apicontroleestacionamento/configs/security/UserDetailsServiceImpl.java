package com.leonardo.apicontroleestacionamento.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leonardo.apicontroleestacionamento.models.UsuarioModel;
import com.leonardo.apicontroleestacionamento.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

//Classe para buscar o username no banco
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        UsuarioModel usuarioModel = usuarioRepository.findByNome(nome)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + nome));
        return new User(usuarioModel.getUsername(), usuarioModel.getPassword(), true, true, true, true, usuarioModel.getAuthorities());
    }
}