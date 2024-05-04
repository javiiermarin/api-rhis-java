package com.rhis.api.mapper;

import com.rhis.api.dto.MarcacionEmpleadoRequestDto;
import com.rhis.api.dto.MarcacionEmpleadoResponseDto;
import com.rhis.api.model.MarcacionEmpleado;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MarcacionEmpleadoMapper {


    private final ModelMapper strictModelMapper;

    public MarcacionEmpleadoMapper(ModelMapper modelMapper, ModelMapper strictModelMapper) {
        this.strictModelMapper = strictModelMapper;
    }

    public MarcacionEmpleadoResponseDto toDto(MarcacionEmpleado marcacionEmpleado){
        return strictModelMapper.map(marcacionEmpleado, MarcacionEmpleadoResponseDto.class);
    }

    public MarcacionEmpleado toEntity(MarcacionEmpleadoRequestDto marcacionEmpleadoRequestDto){
        return strictModelMapper.map(marcacionEmpleadoRequestDto, MarcacionEmpleado.class);


    }


}
