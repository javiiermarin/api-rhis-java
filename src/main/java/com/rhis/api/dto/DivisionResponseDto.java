package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DivisionResponseDto {

    private String idDivision;
    private String nombre;
    private Boolean isEnabled;
    private String encargado;

}
