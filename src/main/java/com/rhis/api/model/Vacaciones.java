package com.rhis.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "dias_disponibles")
    private Integer diasDisponibles;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @OneToMany(targetEntity = VacacionesTracking.class, mappedBy = "vacaciones", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<VacacionesTracking> vacacionesTracking;
}
