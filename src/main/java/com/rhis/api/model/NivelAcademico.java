package com.rhis.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Setter
@Getter
@Entity
@Table(name = "nivel_academico", schema = "rhis_data")
public class NivelAcademico {

    @Id
    @UuidGenerator
    @Column(name = "id_nivel_academico")
    private String idNivelAcademico;

    @Column(name = "nivel_academico")
    private String nivelAcademico;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
}
