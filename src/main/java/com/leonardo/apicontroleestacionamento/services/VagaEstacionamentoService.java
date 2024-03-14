package com.leonardo.apicontroleestacionamento.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leonardo.apicontroleestacionamento.dtos.VagaEstacionamentoRequestDto;
import com.leonardo.apicontroleestacionamento.dtos.VagaEstacionamentoResponseDto;
import com.leonardo.apicontroleestacionamento.models.VagaEstacionamentoModel;
import com.leonardo.apicontroleestacionamento.repositories.VagaEstacionamentoRepository;
import com.leonardo.apicontroleestacionamento.services.exceptions.DatabaseConflictException;
import com.leonardo.apicontroleestacionamento.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class VagaEstacionamentoService {

    @Autowired
    private VagaEstacionamentoRepository vagaEstacionamentoRepository;

    @Transactional
    public VagaEstacionamentoResponseDto salvarVagaEstacionamento(VagaEstacionamentoRequestDto vagaEstacionamentoRequestDto){
        if(vagaEstacionamentoRepository.existsByPlacaCarro(vagaEstacionamentoRequestDto.getPlacaCarro())){
            throw new DatabaseConflictException("Conflito: Placa do carro já está em uso!");
        }
        if(vagaEstacionamentoRepository.existsByNumeroVaga(vagaEstacionamentoRequestDto.getNumeroVaga())){
            throw new DatabaseConflictException("Conflito: Vaga de estacionamento já está em uso!");
        }
        if(vagaEstacionamentoRepository.existsByApartamentoAndBloco(vagaEstacionamentoRequestDto.getApartamento(), vagaEstacionamentoRequestDto.getBloco())){
            throw new DatabaseConflictException("Conflito: Vaga de estacionamento já registrada para este apartamento/bloco!");
        }
        VagaEstacionamentoModel model = new VagaEstacionamentoModel();
        BeanUtils.copyProperties(vagaEstacionamentoRequestDto, model);
        model.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        vagaEstacionamentoRepository.save(model);
        return converterModelParaDto(model);
    }

    public VagaEstacionamentoResponseDto buscarVagaEstacionamentoPorId(UUID id){
        VagaEstacionamentoModel vagaEstacionamentoModel = buscarVagaEstacionamentoModel(id);
        return converterModelParaDto(vagaEstacionamentoModel);
    }

    public Page<VagaEstacionamentoResponseDto> buscarTodasVagasEstacionamento(Pageable pageable){
        return vagaEstacionamentoRepository.buscarTodasVagasEstacionamento(pageable).map(model -> {
            VagaEstacionamentoResponseDto dto = new VagaEstacionamentoResponseDto();
            BeanUtils.copyProperties(model, dto);
            return dto;
        });
    }

    @Transactional
    public VagaEstacionamentoResponseDto atualizarVagaEstacionamento(UUID id, VagaEstacionamentoRequestDto vagaEstacionamentoRequestDto){
        VagaEstacionamentoModel model = buscarVagaEstacionamentoModel(id);
        BeanUtils.copyProperties(vagaEstacionamentoRequestDto, model);
        vagaEstacionamentoRepository.save(model);
        VagaEstacionamentoResponseDto VagaResponseDto = new VagaEstacionamentoResponseDto();
        BeanUtils.copyProperties(model, VagaResponseDto);
        return VagaResponseDto;
    }

    @Transactional
    public void deletarVagaEstacionamento(UUID id){
        buscarVagaEstacionamentoModel(id);
        vagaEstacionamentoRepository.deleteById(id);
    }

    private VagaEstacionamentoModel buscarVagaEstacionamentoModel (UUID id){
        return vagaEstacionamentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vaga de estacionamento não encontrada!"));
    }

    private VagaEstacionamentoResponseDto converterModelParaDto(VagaEstacionamentoModel model){
        VagaEstacionamentoResponseDto dto = new VagaEstacionamentoResponseDto();
        BeanUtils.copyProperties(model, dto);
        return dto;
    }
}