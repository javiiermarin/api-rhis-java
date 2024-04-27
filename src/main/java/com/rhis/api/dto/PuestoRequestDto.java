package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PuestoRequestDto {

    private String nombre;
    private Double salarioMaximo;
    private Double salarioMinimo;
    private String division;
}
