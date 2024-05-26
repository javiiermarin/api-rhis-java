package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class ImpuestoRequestDto {

    private String idImpuesto;

    private String nombre;

    private BigDecimal porcentaje;

    private String descripcion;

}
