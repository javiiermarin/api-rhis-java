package com.rhis.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Setter
@Getter
@Entity
@Table(name = "experiencia_laboral", schema = "rhis_data")
public class ExperienciaLaboral {

    @Id
    @UuidGenerator
    @Column(name = "id_experiencia_laboral")
    private String idExperienciaLaboral;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @Column(name = "antiguedad")
    private int antiguedad;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "puesto")
    private String puesto;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
}
