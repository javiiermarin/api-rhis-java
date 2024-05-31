package com.rhis.api.model;

import com.rhis.api.enums.EstadoCivil;
import com.rhis.api.enums.Genero;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "empleado", schema = "rhis_data")
public class Empleado extends AuditingEntity {

    @Id
    @UuidGenerator
    @Column(name = "id_empleado")
    private String idEmpleado;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "dpi")
    private String dpi;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "genero")
    private Genero genero;

    @Column(name = "correo")
    private String correo;

    @Column(name = "nit")
    private String nit;

    @Column(name = "nacionalidad")
    private String nacionalidad;

    @Column(name = "nivel_ingles")
    private String nivelIngles;

    @CreationTimestamp
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    @Column(name = "habilitado")
    private Boolean habilitado;

    @Column(name = "estado_civil")
    private EstadoCivil estadoCivil;

    @Column(name = "salario")
    private BigDecimal salario;

    @OneToOne
    @JoinColumn(name = "id_puesto")
    private Puesto puesto;

    @OneToMany(targetEntity = EmpleadoReferencia.class, mappedBy = "empleado", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EmpleadoReferencia> empleadoReferencia;

    @OneToMany(targetEntity = ExperienciaLaboral.class, mappedBy = "empleado", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ExperienciaLaboral> experienciaLaboral;

    @OneToMany(targetEntity = NivelAcademico.class, mappedBy = "empleado", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<NivelAcademico> nivelAcademico;

}
