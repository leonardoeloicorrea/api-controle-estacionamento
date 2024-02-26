package com.leonardo.apicontroleestacionamento.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.apicontroleestacionamento.dtos.VagaEstacionamentoRequestDto;
import com.leonardo.apicontroleestacionamento.dtos.VagaEstacionamentoResponseDto;
import com.leonardo.apicontroleestacionamento.services.VagaEstacionamentoService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/vaga-estacionamento")
public class VagaEstacionamentoController {

    @Autowired
    private VagaEstacionamentoService vagaEstacionamentoService;

    @PostMapping
    public ResponseEntity<VagaEstacionamentoResponseDto> salvarVagaEstacionamento(@RequestBody @Valid VagaEstacionamentoRequestDto vagaEstacionamentoRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(vagaEstacionamentoService.salvarVagaEstacionamento(vagaEstacionamentoRequestDto));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<VagaEstacionamentoResponseDto> buscarVagaEstacionamentoModel (@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(vagaEstacionamentoService.buscarVagaEstacionamentoPorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<VagaEstacionamentoResponseDto>> buscarTodasVagasEstacionamento(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(vagaEstacionamentoService.buscarTodasVagasEstacionamento(pageable));
    }
    
    @PutMapping(value = "{id}")
    public ResponseEntity<VagaEstacionamentoResponseDto> atualizarVagaEstacionamento(@PathVariable UUID id, @RequestBody @Valid VagaEstacionamentoRequestDto vagaEstacionamentoRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(vagaEstacionamentoService.atualizarVagaEstacionamento(id, vagaEstacionamentoRequestDto));
    }
}
