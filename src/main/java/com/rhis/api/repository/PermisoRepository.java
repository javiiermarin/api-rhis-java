package com.rhis.api.repository;

import com.rhis.api.model.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermisoRepository extends JpaRepository<Permiso, String> {

    List<Permiso> findAllByEmpleadoIdEmpleado(String idEmpleado);


}
