package com.leonardo.apicontroleestacionamento.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.leonardo.apicontroleestacionamento.models.VagaEstacionamentoModel;

@Repository
public interface VagaEstacionamentoRepository extends JpaRepository<VagaEstacionamentoModel, UUID> {
    
    boolean existsByPlacaCarro(String placaCarro);
    boolean existsByNumeroVaga(String numeroVaga);
    boolean existsByApartamentoAndBloco(String apartamento, String bloco);

    @Query("SELECT obj "
        + "FROM VagaEstacionamentoModel obj")
    Page<VagaEstacionamentoModel> buscarTodasVagasEstacionamento(Pageable pageable);

}