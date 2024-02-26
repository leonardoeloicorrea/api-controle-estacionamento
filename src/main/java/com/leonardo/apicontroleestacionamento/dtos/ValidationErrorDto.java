package com.leonardo.apicontroleestacionamento.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDto extends CustomErrorDto {

    List<FieldMessageDto> errors = new ArrayList<>();

    public ValidationErrorDto(LocalDateTime timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessageDto> getErrors() {
        return errors;
    }

    public void addError(FieldMessageDto error) {
        errors.add(error);
    }

}