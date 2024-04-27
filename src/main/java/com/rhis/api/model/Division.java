package com.rhis.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Setter
@Getter
@Entity
@Table(name = "division", schema = "rhis_data")
public class Division extends AuditingEntity{

    @Id
    @UuidGenerator
    @Column(name = "id_division")
    private String idDivision;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @ManyToOne
    @JoinColumn(name = "id_encargado")
    private Empleado encargado;

}
