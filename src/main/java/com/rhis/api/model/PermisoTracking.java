package com.rhis.api.model;

import com.rhis.api.enums.EstadoPermiso;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Setter
@Getter
@Entity
@Table(name = "permiso_tracking", schema = "rhis_data")
public class PermisoTracking {

    @Id
    @UuidGenerator
    @Column(name = "id_permiso_tracking")
    private String idPermisoTracking;

    @Column(name = "estado")
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_permiso")
    private Permiso permiso;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
}
