package com.rhis.api.repository;

import com.rhis.api.model.MarcacionEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MarcacionEmpleadoRepository extends JpaRepository<MarcacionEmpleado, String> {

    @Query("SELECT me FROM MarcacionEmpleado me WHERE me.fecha = CURRENT_DATE AND me.empleado.idEmpleado = :id_empleado ORDER BY me.idMarcacionEmpleado DESC")
    MarcacionEmpleado idMarcacionEmpleado(@Param("id_empleado") String idEmpleado);


}
