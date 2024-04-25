package com.rhis.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Setter
@Getter
@Entity
@Table(name = "empleado_referencia", schema = "rhis_data")
public class EmpleadoReferencia {

    @Id
    @UuidGenerator
    @Column(name = "id_empleado_referencia")
    private String idEmpleadoReferencia;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(targetEntity = Empleado.class)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;
}
