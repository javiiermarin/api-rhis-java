package com.rhis.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "planilla_impuesto", schema = "rhis_data")
public class PlanillaImpuesto {

    @Id
    @UuidGenerator
    private String idPlanilla_impuesto;

    private Double calcululo;

    @ManyToOne
    @JoinColumn(name = "id_pago_planilla")
    private PagoPlanilla pagoPlanilla;

    @ManyToOne
    @JoinColumn(name = "id_impuesto")
    private Impuesto impuesto;
}
