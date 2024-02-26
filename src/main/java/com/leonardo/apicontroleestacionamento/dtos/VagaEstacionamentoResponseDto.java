package com.leonardo.apicontroleestacionamento.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VagaEstacionamentoResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Campo obrigatório")
    private String numeroVaga;

    @NotBlank(message = "Campo obrigatório")
    @Size(max = 7, message = "Maximo de 7 caracteres")
    private String placaCarro;

    @NotBlank(message = "Campo obrigatório")
    private String marcaCarro;

    @NotBlank(message = "Campo obrigatório")
    private String modeloCarro;
    
    @NotBlank(message = "Campo obrigatório")
    private String corCarro;

    @NotBlank(message = "Campo obrigatório")
    private LocalDateTime dataRegistro;

    @NotBlank(message = "Campo obrigatório")
    private String nomeResponsavel;
    
    @NotBlank(message = "Campo obrigatório")
    private String apartamento;

    @NotBlank(message = "Campo obrigatório")
    private String bloco;

}
