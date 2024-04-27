package com.rhis.api.mapper;

import com.rhis.api.dto.JornadaRequestDto;
import com.rhis.api.dto.JornadaResponseDto;
import com.rhis.api.model.Jornada;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class JornadaMapper {

    private final ModelMapper modelMapper;

    public JornadaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public JornadaResponseDto toDto (Jornada jornada){
        return modelMapper.map(jornada, JornadaResponseDto.class);
    }

    public Jornada toEntity(JornadaRequestDto jornadaRequestDto){
        return modelMapper.map(jornadaRequestDto, Jornada.class);
    }



}
