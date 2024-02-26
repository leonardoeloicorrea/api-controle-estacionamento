package com.leonardo.apicontroleestacionamento.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomErrorDto {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;

}
