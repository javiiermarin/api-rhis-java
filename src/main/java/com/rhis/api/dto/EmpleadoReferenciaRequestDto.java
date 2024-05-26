package com.rhis.api.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class EmpleadoReferenciaRequestDto {

    private String idEmpleadoReferencia;

    @NotNull
    @Size(max = 50)
    private String nombres;

    @NotNull
    @Size(max = 50)
    private String apellidos;

    @NotNull
    @Size(max = 15)
    private String telefono;

    @Size(max = 100)
    private String descripcion;

    @Column(name = "id_empleado")
    @JsonBackReference
    private EmpleadoRequestDto empleado;
}
