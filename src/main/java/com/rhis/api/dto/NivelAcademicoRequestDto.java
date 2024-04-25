package com.rhis.api.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NivelAcademicoRequestDto {

    @NotNull
    @Size(max = 100)
    private String descripcion;

    @JsonBackReference
    private EmpleadoRequestDto empleado;
}
