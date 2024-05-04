package com.rhis.api.repository;

import com.rhis.api.model.Vacaciones;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacacionesRepository extends JpaRepository<Vacaciones, String> {

    List<Vacaciones> findAllByEmpleadoPuestoDivisionIdDivision(String idDivision);

}
