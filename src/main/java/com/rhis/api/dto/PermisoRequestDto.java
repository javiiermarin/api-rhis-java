package com.rhis.api.dto;

import com.rhis.api.model.Empleado;
import com.rhis.api.model.TipoPermiso;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class PermisoRequestDto {

    @NotNull
    @Size(max = 200)
    private String descripcion;

    @NotNull
    private LocalDateTime fechaInicio;

    @NotNull
    private LocalDateTime fechaFinal;

    @NotNull
    private Empleado empleado;

    @NotNull
    private TipoPermiso tipoPermiso;

}
