package com.rhis.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class EmpleadoRequestDto {

    @NotNull
    @Size(max = 50)
    private String nombres;

    @NotNull
    @Size(max = 50)
    private String apellidos;

    @NotNull
    @Size(max = 50)
    private String dpi;

    @NotNull
    @Size(max = 15)
    private String telefono;

    @NotNull
    @Size(max = 100)
    private String direccion;

    @NotNull
    @Size(max = 25)
    private String genero;

    @Email
    @Size(max = 150)
    private String correo;

    @NotNull
    @Size(max = 20)
    private String nit;

    @Size(max = 50)
    private String nacionalidad;

    @Size(max = 40)
    private String nivelIngles;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaIngreso;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaSalida;

    @NotNull
    @Size(max = 50)
    private String estadoCivil;

    @NotNull
    @Size(max = 36)
    private String puesto;

    @JsonManagedReference
    private List<EmpleadoReferenciaRequestDto> empleadoReferencia;

    @JsonManagedReference
    private List<ExperienciaLaboralRequestDto> experienciaLaboral;

    @JsonManagedReference
    private List<NivelAcademicoRequestDto> nivelAcademico;
}
