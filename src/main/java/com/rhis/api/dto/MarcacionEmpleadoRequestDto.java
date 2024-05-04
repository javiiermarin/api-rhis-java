package com.rhis.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class MarcacionEmpleadoRequestDto {

    @NotBlank
    private String empleado;
}
