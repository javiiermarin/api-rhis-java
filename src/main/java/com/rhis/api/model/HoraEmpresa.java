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
@Table(name = "hora_empresa", schema = "rhis_data")
public class HoraEmpresa {

    @Id
    @UuidGenerator
    @Column(name = "id_hora_empresa")
    private String idHoraEmpresa;

    @Column(name = "hora_entrada")
    private LocalTime horaEntrada;

    @Column(name = "hora_salida_almuerzo")
    private LocalTime horaSalidaAlmuerzo;

    @Column(name = "hora_entrada_almuerzo")
    private LocalTime horaEntradaAlmuerzo;

    @Column(name = "hora_salida")
    private LocalTime horaSalida;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "habilitado")
    private Boolean habilitado;
}
