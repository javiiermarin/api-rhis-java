package com.rhis.api.service;

import com.rhis.api.dto.PuestoResponseDto;
import com.rhis.api.exception.DivisionNotFoundException;
import com.rhis.api.exception.PuestoNotFoundException;
import com.rhis.api.mapper.PuestoMapper;
import com.rhis.api.repository.DivisionRespository;
import com.rhis.api.repository.PuestoRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuestoService {

    private final PuestoRespository puestoRespository;

    private final PuestoMapper puestoMapper;

    private final DivisionRespository divisionRespository;

    public PuestoService(PuestoRespository puestoRespository, PuestoMapper puestoMapper, DivisionRespository divisionRespository) {
        this.puestoRespository = puestoRespository;
        this.puestoMapper = puestoMapper;
        this.divisionRespository = divisionRespository;
    }

    public List<PuestoResponseDto> obtenerPuestosPorDivision(String idDivisiom)throws DivisionNotFoundException {

        var puestoResponseDto = puestoRespository.findAllByDivision_IdDivisionAndIsEnabledTrue(idDivisiom)
                .stream()
                .map(puestoMapper::toDto)
                .toList();
        return puestoResponseDto;
    }


}
