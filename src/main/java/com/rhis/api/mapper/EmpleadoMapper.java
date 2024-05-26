package com.rhis.api.mapper;

import com.rhis.api.dto.EmpleadoRequestDto;
import com.rhis.api.dto.EmpleadoResponseDto;
import com.rhis.api.model.Empleado;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {

    private final ModelMapper modelMapper;

    public EmpleadoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EmpleadoResponseDto toDto(Empleado empleado){
        return modelMapper.map(empleado, EmpleadoResponseDto.class);
    }

    public Empleado toEntity(EmpleadoRequestDto empleadoRequestDto){
        return modelMapper.map(empleadoRequestDto, Empleado.class);
    }

    public void modificar(EmpleadoRequestDto empleadoRequestDto, Empleado empleado){
        empleado.setNombres(empleadoRequestDto.getNombres());
        empleado.setApellidos(empleadoRequestDto.getApellidos());
        empleado.setDpi(empleadoRequestDto.getDpi());

    }

}
