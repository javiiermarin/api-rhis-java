package com.rhis.api.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DivisionRequestDto {


    @Size(max = 36)
    private String idDivision;

    @Size(max = 50)
    private String nombre;

    private boolean enabled;

    @Size(max = 36)
    private String encargado;
}
