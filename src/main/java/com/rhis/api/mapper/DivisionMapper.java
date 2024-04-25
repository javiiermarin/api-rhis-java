package com.rhis.api.mapper;

import com.rhis.api.dto.DivisionResponseDto;
import com.rhis.api.model.Division;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DivisionMapper {

    private final ModelMapper modelMapper;

    public DivisionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DivisionResponseDto toDto(Division division){
        return modelMapper.map(division, DivisionResponseDto.class);
    }



}
