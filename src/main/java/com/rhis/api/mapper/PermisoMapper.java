package com.rhis.api.mapper;

import com.rhis.api.dto.PermisoRequestDto;
import com.rhis.api.dto.PermisoResponseDto;
import com.rhis.api.model.Permiso;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PermisoMapper {

    private final ModelMapper modelMapper;

    public PermisoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PermisoResponseDto toDto (Permiso permiso){
        return modelMapper.map(permiso, PermisoResponseDto.class);
    }

    public Permiso toEntity (PermisoRequestDto permisoRequestDto){
        return modelMapper.map(permisoRequestDto, Permiso.class);
    }
}
