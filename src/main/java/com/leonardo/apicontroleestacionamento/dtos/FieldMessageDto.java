package com.leonardo.apicontroleestacionamento.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FieldMessageDto {

    private String fieldName;
    private String message;

}