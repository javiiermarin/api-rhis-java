package com.rhis.api.mapper;

import com.rhis.api.dto.PuestoRequestDto;
import com.rhis.api.dto.PuestoResponseDto;
import com.rhis.api.model.Puesto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PuestoMapper {

    private final ModelMapper modelMapper;

    public PuestoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PuestoResponseDto toDto(Puesto puesto){
        return modelMapper.map(puesto, PuestoResponseDto.class);
    }

    public Puesto toEntity(PuestoRequestDto puestoRequestDto){
        return modelMapper.map(puestoRequestDto, Puesto.class);
    }
}
