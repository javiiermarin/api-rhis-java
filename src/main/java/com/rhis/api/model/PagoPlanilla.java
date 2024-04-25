package com.rhis.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "pago_planilla", schema = "rhis_data")
public class PagoPlanilla {

    @Id
    @UuidGenerator
    @Column(name = "id_pago_planilla" )
    private String idPagoPlanilla;

    @Column(name = "salario")
    private Double salario;

    @Column(name = "bonificacion_ley")
    private Double bonificacionLey;

    @Column(name = "sueldo_base")
    private Double sueldoBase;

    @Column(name = "sueldo_neto")
    private Double sueldoNeto;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_final")
    private LocalDateTime fechaFinal;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;




}
