package com.rhis.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "impuesto", schema = "rhis_data")
public class Impuesto {

    @Id
    @UuidGenerator
    @Column(name = "id_impuesto")
    private String idImpuesto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "porcentaje")
    private BigDecimal porcentaje;

    @Column(name = "descripcion")
    private String descripcion;

}
