package com.rhis.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rhis.api.model.VacacionesTracking;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class VacacionesRequestDto {

    @Size(max = 36)
    private String idVacaciones;

    @NotNull
    @JsonFormat(pattern = "yyy-MM-dd")
    private LocalDate fechaInicio;

    @NotNull
    @JsonFormat(pattern = "yyy-MM-dd")
    private LocalDate fechaFinal;

    private String descripcion;

    private List<VacacionesTracking> vacacionesTracking;

}
