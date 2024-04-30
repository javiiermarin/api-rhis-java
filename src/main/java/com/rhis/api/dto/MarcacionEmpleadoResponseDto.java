package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class MarcacionEmpleadoResponseDto {


    private String idMarcacionEmpleado;
    private String idMarcacion;
    private String idEmpleado;
    private LocalDateTime hora;
}
