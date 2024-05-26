package com.rhis.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@Entity
@Table(name = "marcacion_random", schema = "rhis_data")
public class MarcacionRandom {

    @Id
    @UuidGenerator
    @Column(name = "id_marcacion_random")
    private String idMarcacionRandom;

    @Column(name = "hora")
    private LocalTime hora;


    @CreationTimestamp
    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
}
