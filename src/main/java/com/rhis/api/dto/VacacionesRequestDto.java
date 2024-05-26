package com.rhis.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rhis.api.model.VacacionesTracking;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@ToString
public class VacacionesRequestDto {

    @Column(name = "id_vacaciones")
    private String idVacaciones;

    @NotNull
    @JsonFormat(pattern = "yyy-MM-dd")
    private LocalDate fechaInicio;

    @NotNull
    @JsonFormat(pattern = "yyy-MM-dd")
    private LocalDate fechaFinal;

    private String descripcion;

    @NotNull
    private String empleado;

    private List<VacacionesTracking> vacacionesTracking;

}
