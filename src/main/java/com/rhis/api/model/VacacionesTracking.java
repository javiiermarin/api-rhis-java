package com.rhis.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Setter
@Getter
@Entity
@Table(name = "vacaciones_tracking", schema = "rhis_data")
public class VacacionesTracking {

    @Id
    @UuidGenerator
    @Column(name = "id_vacaciones_tracking")
    private String idVacacionesTracking;

    @Column(name = "estado")
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_vacaciones")
    private Vacaciones vacaciones;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

}
