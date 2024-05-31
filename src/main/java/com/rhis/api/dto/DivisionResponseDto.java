package com.rhis.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class DivisionResponseDto {

    private String idDivision;
    private String nombre;
    private boolean enabled;
    private String encargado;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
}
