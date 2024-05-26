package com.rhis.api.mapper;

import com.rhis.api.dto.ImpuestoRequestDto;
import com.rhis.api.dto.ImpuestosResponseDto;
import com.rhis.api.model.Impuesto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ImpuestosMapper {

    private final ModelMapper modelMapper;


    public ImpuestosMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ImpuestosResponseDto toDto(Impuesto impuesto) {
        return modelMapper.map(impuesto, ImpuestosResponseDto.class);
    }

    public Impuesto toEntity(ImpuestoRequestDto impuestoRequestDto) {
        return modelMapper.map(impuestoRequestDto, Impuesto.class);
    }
}
