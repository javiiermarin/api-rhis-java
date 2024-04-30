package com.rhis.api.mapper;

import com.rhis.api.dto.PagoPlanillaResponseDto;
import com.rhis.api.model.PagoPlanilla;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PagoPlanillaMapper {

    private final ModelMapper modelMapper;

    public PagoPlanillaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PagoPlanillaResponseDto toDto(PagoPlanilla pagoPlanilla) {
        return modelMapper.map(pagoPlanilla, PagoPlanillaResponseDto.class);
    }

    public PagoPlanilla toEntity(PagoPlanillaResponseDto pagoPlanillaResponseDto) {
        return modelMapper.map(pagoPlanillaResponseDto, PagoPlanilla.class);
    }
}
