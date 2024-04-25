package com.rhis.api.service;

import com.rhis.api.dto.DivisionResponseDto;
import com.rhis.api.exception.DivisionNotFoundException;
import com.rhis.api.mapper.DivisionMapper;
import com.rhis.api.repository.DivisionRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisionService {

    private final DivisionRespository divisionRespository;

    private final DivisionMapper divisionMapper;

    public DivisionService(DivisionRespository divisionRespository, DivisionMapper divisionMapper) {
        this.divisionRespository = divisionRespository;
        this.divisionMapper = divisionMapper;
    }

    /**
     * Funcion que obtiene todas las divisiones (departamentos dentro de la empresa) disponibles
     * @return listado de divisiones
     */
    public List<DivisionResponseDto> obtenerDivisiones()throws DivisionNotFoundException {
        var divisionResponseDto = divisionRespository.findAllByIsEnabledTrue()
                .stream()
                .map(divisionMapper::toDto)
                .toList();
        return divisionResponseDto;
    }


}
