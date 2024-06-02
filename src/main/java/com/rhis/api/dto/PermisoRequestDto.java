package com.rhis.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rhis.api.model.PermisoTracking;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
public class PermisoRequestDto {

    @Size(max = 36)
    private String idPermiso;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFinal;

    @Size(max = 200)
    private String descripcion;

    @NotNull
    private String tipoPermiso;

    private List<PermisoTracking> permisoTracking;
}
