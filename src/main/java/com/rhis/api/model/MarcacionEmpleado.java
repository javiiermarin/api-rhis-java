package com.rhis.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "marcacion_empleado", schema = "rhis_data")
public class MarcacionEmpleado {

    @Id
    @UuidGenerator
    @Column(name = "id_marcacion_empleado")
    private String idMarcacionEmpleado;

    @CreationTimestamp
    @Column(name = "hora")
    private LocalDateTime hora;

    @ManyToOne
    @JoinColumn(name = "id_marcacion")
    private Marcacion marcacion;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

}
