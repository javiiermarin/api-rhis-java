package com.rhis.api.dto;

import com.rhis.api.enums.EstadoCivil;
import com.rhis.api.enums.Genero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class EmpleadoResponseDto {

    private String idEmpleado;
    private String nombres;
    private String apellidos;
    private String dpi;
    private String telefono;
    private String direccion;
    private Genero genero;
    private String correo;
    private String nit;
    private String username;
    private String nacionalidad;
    private String nivelIngles;
    private LocalDateTime fechaRegistro;
    private LocalDate fechaNacimiento;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private String role;
    private EstadoCivil estadoCivil;
    private BigDecimal salario;
    private PuestoResponseDto puesto;
    private List<EmpleadoReferenciaResponseDto> empleadoReferencia;
    private List<ExperienciaLaboralResponseDto> experienciaLaboral;
    private List<NivelAcademicoResponseDto> nivelAcademico;
}
