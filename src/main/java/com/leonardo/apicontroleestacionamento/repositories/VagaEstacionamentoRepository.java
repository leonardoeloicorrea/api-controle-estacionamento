package com.leonardo.apicontroleestacionamento.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonardo.apicontroleestacionamento.models.VagaEstacionamentoModel;

@Repository
public interface VagaEstacionamentoRepository extends JpaRepository<VagaEstacionamentoModel, UUID> {
    
}