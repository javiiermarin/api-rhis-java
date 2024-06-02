package com.rhis.api.mapper;

import com.rhis.api.dto.PuestoRequestDto;
import com.rhis.api.dto.PuestoResponseDto;
import com.rhis.api.model.Puesto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PuestoMapper {

    private final ModelMapper strictModelMapper;

    public PuestoMapper(ModelMapper strictModelMapper) {
        this.strictModelMapper = strictModelMapper;
    }

    public PuestoResponseDto toDto(Puesto puesto) {
        var puestoResponse = strictModelMapper.map(puesto, PuestoResponseDto.class);

        if (puesto.getDivision().getEncargado() != null) {
            puestoResponse.getDivision().setEncargado(puesto.getDivision().getEncargado().getIdEmpleado());
        }
        return puestoResponse;
    }

    public Puesto toEntity(PuestoRequestDto puestoRequestDto) {
        return strictModelMapper.map(puestoRequestDto, Puesto.class);
    }
}
