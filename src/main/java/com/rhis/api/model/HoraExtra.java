package com.rhis.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "hora_extra", schema = "rhis_data")
public class HoraExtra {

    @Id
    @UuidGenerator
    @Column(name = "id_hora_extra")
    private String idHoraExtra;

    @Column(name = "hora_inicio")
    private LocalDateTime horaInicio;

    @Column(name = "hora_final")
    private LocalDateTime horaFinal;

    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

}
