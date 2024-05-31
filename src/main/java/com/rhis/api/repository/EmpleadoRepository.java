package com.rhis.api.repository;

import com.rhis.api.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, String> {

    List<Empleado> findAllByPuestoIdPuestoAndHabilitadoTrue(String idPuesto);

    Optional<Empleado> findByPuestoIdPuesto(String idPuesto);

    List<Empleado> findAllByPuestoDivisionIdDivisionAndHabilitadoTrue(String idDivision);

    List<Empleado> findAllByOrderByFechaIngresoAsc();



}
