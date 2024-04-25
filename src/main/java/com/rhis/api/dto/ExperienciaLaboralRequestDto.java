package com.rhis.api.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rhis.api.model.Empleado;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExperienciaLaboralRequestDto {

    @NotNull
    @Size(max = 50)
    private String nombreEmpresa;

    @NotNull
    @Positive
    private int antiguedad;

    @Size(max = 15)
    private String telefono;

    @Size(max = 50)
    private String puesto;

    @Size(max = 100)
    private String descripcion;

    @JsonBackReference
    private EmpleadoRequestDto empleado;
}
