package com.rhis.api.repository;

import com.rhis.api.model.Division;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DivisionRespository extends JpaRepository<Division, String> {

    List<Division> findAllByIsEnabledTrue();

}
