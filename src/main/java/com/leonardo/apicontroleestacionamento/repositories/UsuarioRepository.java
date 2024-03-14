package com.leonardo.apicontroleestacionamento.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonardo.apicontroleestacionamento.models.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID>{
    
    Optional<UsuarioModel> findByNome(String nome);

}