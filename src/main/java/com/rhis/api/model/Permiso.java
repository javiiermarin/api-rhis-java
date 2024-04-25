package com.rhis.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "permiso", schema = "rhis_data")
public class Permiso {

    @Id
    @UuidGenerator
    @Column(name = "id_permiso")
    private String idPermiso;

    @Column(name = "descripcion")
    private String descripcion;

    @CreationTimestamp
    @Column(name = "fecha_solicitud")
    private LocalDateTime fechaSolicitud;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_final")
    private LocalDate fechaFinal;

    @ManyToOne(targetEntity = Empleado.class)
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @ManyToOne(targetEntity = TipoPermiso.class)
    @JoinColumn(name = "tipo_permiso")
    private TipoPermiso tipoPermiso;

    @OneToMany(targetEntity = PermisoTracking.class, mappedBy = "permiso", cascade = CascadeType.ALL)
    private List<PermisoTracking> permisoTracking;
}
