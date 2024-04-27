package com.rhis.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DivisionRequestDto {


    @Size(max = 50)
    private String nombre;

    private Boolean isEnabled;

    @NotNull
    @Size(max = 36)
    private String encargado;
}
