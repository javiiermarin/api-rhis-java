package com.rhis.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Setter
@Getter
@Entity
@Table(name = "puesto", schema = "rhis_data")
public class Puesto {

    @Id
    @UuidGenerator
    @Column(name = "id_puesto")
    private String idPuesto;

    private String nombre;

    @Column(name = "salario_maximo")
    private Double salarioMaximo;

    @Column(name = "salario_minimo")
    private Double salarioMinimo;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @ManyToOne()
    @JoinColumn(name = "id_division")
    private Division division;

}
