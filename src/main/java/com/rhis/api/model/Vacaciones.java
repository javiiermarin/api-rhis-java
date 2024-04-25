package com.rhis.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "vacaciones", schema = "rhis_data")
public class Vacaciones {

    @Id
    @UuidGenerator
    @Column(name = "id_vacaciones")
    private String idVacaciones;

    @CreationTimestamp
    @Column(name = "fecha_solicitud")
    private LocalDateTime fechaSolicitud;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_final")
    private LocalDate fechaFinal;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
}
