package com.rhis.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Setter
@Getter
@Entity
@Table(name = "planilla_hora_extra")
public class PlanillaHoraExtra {

    @Id
    @UuidGenerator
    @Column(name = "id_planilla_hora_extra")
    private String idPlanillaHoraExtra;

    private Integer cantidadHoras;

    private Double calculo;

    @ManyToOne
    @JoinColumn(name = "id_pago_planilla")
    private PagoPlanilla pagoPlanilla;

    @ManyToOne
    @JoinColumn(name = "id_hora_extra")
    private HoraExtra horaExtra;

}
