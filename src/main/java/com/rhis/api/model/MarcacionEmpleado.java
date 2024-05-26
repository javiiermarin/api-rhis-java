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
@Table(name = "marcacion_empleado", schema = "rhis_data")
public class MarcacionEmpleado {

    @Id
    @UuidGenerator
    @Column(name = "id_marcacion_empleado")
    private String idMarcacionEmpleado;


    @Column(name = "hora_entrada")
    private LocalTime horaEntrada;


    @Column(name = "hora_salida_almuerzo")
    private LocalTime horaSalidaAlmuerzo;


    @Column(name = "hora_entrada_almuerzo")
    private LocalTime horaEntradaAlmuerzo;


    @Column(name = "hora_salida")
    private LocalTime horaSalida;

    @CreationTimestamp
    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @OneToOne
    @JoinTable(name = "marcacion_hora_extra",
                joinColumns = @JoinColumn(name = "id_marcacion_empleado"),
                inverseJoinColumns = @JoinColumn(name = "id_hora_extra"))
    private HoraExtra horaExtra;

}
