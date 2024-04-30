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
@Table(name = "marcacion", schema = "rhis_data")
public class Marcacion {

    @Id
    @UuidGenerator
    @Column(name = "id_marcacion")
    private String idMarcacion;

    @Column(name = "hora_entrada")
    private LocalTime horaEntrada;

    @Column(name = "hora_salida_almuerzo")
    private LocalTime horaSalidaAlmuerzo;

    @Column(name = "hora_entrada_almuerzo")
    private LocalTime horaEntradaAlmuerzo;

    @Column(name = "hora_salida")
    private LocalTime horaSalida;

    @Column(name = "tiempo_antes")
    private LocalTime tiempoAntes;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "habilitado")
    private Boolean habilitado;
}
