package com.rhis.api.repository;

import com.rhis.api.model.Puesto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PuestoRespository extends JpaRepository<Puesto, String> {

    List<Puesto> findAllByDivision_IdDivisionAndIsEnabledTrue(String id_division);



}
