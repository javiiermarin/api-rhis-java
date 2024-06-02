package com.rhis.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rhis.api.enums.EstadoCivil;
import com.rhis.api.enums.Genero;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@ToString
public class EmpleadoRequestDto {

    @Size(max = 36)
    private String idEmpleado;

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

    private Genero genero;

    @Email
    @Size(max = 150)
    private String correo;

    @Size(max = 20)
    private String nit;

    @NotNull
    private String role;

    @Size(max = 50)
    private String nacionalidad;

    @Size(max = 40)
    private String nivelIngles;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaIngreso;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaSalida;

    private EstadoCivil estadoCivil;

    private BigDecimal salario;

    @Size(max = 36)
    private String puesto;

    @JsonManagedReference
    private List<EmpleadoReferenciaRequestDto> empleadoReferencia;

    @JsonManagedReference
    private List<ExperienciaLaboralRequestDto> experienciaLaboral;

    @JsonManagedReference
    private List<NivelAcademicoRequestDto> nivelAcademico;
}
