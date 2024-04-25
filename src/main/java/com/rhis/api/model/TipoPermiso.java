package com.rhis.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Setter
@Getter
@Entity
@Table(name = "tipo_permiso", schema ="rhis_data" )
public class TipoPermiso {

    @Id
    @UuidGenerator
    @Column(name = "id_tipo_permiso")
    private String idTipoPermiso;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "habilitado")
    private Boolean habilitado;
}
