package com.rhis.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PagoPlanillaRequestDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFinal;
}
