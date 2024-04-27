package com.rhis.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalTime;

@Setter
@Getter
@Entity
@Table(name = "jornada", schema = "rhis_data")
public class Jornada {

    @Id
    @UuidGenerator
    @Column(name = "id_jornada")
    private String idJornada;

    @Column(name = "jornada")
    private String jornada;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;

    @Column(name = "valor")
    private Double valor;
}
