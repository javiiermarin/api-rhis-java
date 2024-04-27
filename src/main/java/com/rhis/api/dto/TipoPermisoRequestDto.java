package com.rhis.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TipoPermisoRequestDto {

    @NotNull
    @Size(max = 50)
    private String nombre;

    @Size(max = 200)
    private String descripcion;

}
