package com.leonardo.apicontroleestacionamento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
}
