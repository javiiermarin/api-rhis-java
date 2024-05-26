package com.rhis.api.mapper;

import com.rhis.api.dto.MarcacionRandomResponseDto;
import com.rhis.api.model.MarcacionRandom;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MarcacionRandomMapper {

    private final ModelMapper modelMapper;

    public MarcacionRandomMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MarcacionRandomResponseDto toDto (MarcacionRandom marcacionRandom){
        return modelMapper.map(marcacionRandom, MarcacionRandomResponseDto.class);
    }
}
