package com.rhis.api.mapper;

import com.rhis.api.dto.VacacionesRequestDto;
import com.rhis.api.dto.VacacionesResponseDto;
import com.rhis.api.model.Vacaciones;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VacacionesMapper {

    private final ModelMapper modelMapper;

    public VacacionesMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public VacacionesResponseDto toDto(Vacaciones vacaciones) {
        return modelMapper.map(vacaciones, VacacionesResponseDto.class);
    }

    public Vacaciones toEntity(VacacionesRequestDto vacacionesRequestDto) {
        Vacaciones vacaciones = new Vacaciones();
        vacaciones.setDescripcion(vacacionesRequestDto.getDescripcion());
        vacaciones.setFechaFinal(vacacionesRequestDto.getFechaFinal());
        vacaciones.setFechaInicio(vacacionesRequestDto.getFechaInicio());

        return vacaciones;
    }

    public void modificar (VacacionesRequestDto vacacionesRequestDto, Vacaciones vacaciones){
        vacaciones.setFechaInicio(vacacionesRequestDto.getFechaInicio());
        vacaciones.setFechaFinal(vacacionesRequestDto.getFechaFinal());
        vacaciones.setDescripcion(vacacionesRequestDto.getDescripcion());
    }
}
