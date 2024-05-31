package com.rhis.api.repository;

import com.rhis.api.model.Vacaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VacacionesRepository extends JpaRepository<Vacaciones, String> {

    List<Vacaciones> findAllByEmpleadoPuestoDivisionIdDivision(String idDivision);

    @Query("SELECT v FROM Vacaciones v " +
            "JOIN v.vacacionesTracking vt " +
            "WHERE v.empleado.idEmpleado = :idUsuario " +
            "OR vt.empleado.idEmpleado = :idEmpleadoTracking")
    List<Vacaciones> findVacacionesByEmpleadoAndTrackingEmpleadoOrderByCreatedAtDesc(String idUsuario, String idEmpleadoTracking);

}
