package com.rhis.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Column(name = "bonificacion_ley")
    private BigDecimal bonificacionLey;

    @Column(name = "sueldo_base")
    private BigDecimal sueldoBase;

    @Column(name = "salario_neto")
    private BigDecimal salarioNeto;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_final")
    private LocalDate fechaFinal;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
}
