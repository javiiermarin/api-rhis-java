package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ImpuestosResponseDto {

    private String idImpuesto;

    private String nombre;

    private BigDecimal porcentaje;

    private String descripcion;

}
