package com.rhis.api.repository;

import com.rhis.api.model.HoraExtra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoraExtraRepository extends JpaRepository<HoraExtra, String> {

    List<HoraExtra> findAllByEmpleadoIdEmpleado(String idEmpleado);

}
