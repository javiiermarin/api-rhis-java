package com.rhis.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "empresa", schema = "rhis_data")
public class Empresa {

    @Id
    @UuidGenerator
    @Column(name = "id_empresa")
    private String idEmpresa;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "representante_legal")
    private String representanteLegal;

    @Column(name = "fecha_fundacion")
    private LocalDate fechaFundacion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "nit")
    private String nit;

    @Column(name = "correo")
    private String correo;

    @Column(name = "mision")
    private String mision;

    @Column(name = "vision")
    private String vision;
}
