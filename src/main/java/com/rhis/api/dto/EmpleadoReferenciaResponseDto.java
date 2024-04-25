package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmpleadoReferenciaResponseDto {

    private String idEmpleadoReferencia;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String descripcion;

}
