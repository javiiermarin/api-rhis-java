package com.rhis.api.mapper;

import com.rhis.api.dto.PermisoTrackingRequestDto;
import com.rhis.api.dto.PermisoTrackingResponseDto;
import com.rhis.api.model.PermisoTracking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PermisoTrackingMapper {

    private final ModelMapper modelMapper;
    private final PermisoMapper permisoMapper;

    public PermisoTrackingMapper(ModelMapper modelMapper, PermisoMapper permisoMapper) {
        this.modelMapper = modelMapper;
        this.permisoMapper = permisoMapper;
    }

    public PermisoTrackingResponseDto toDto(PermisoTracking permisoTracking){
        return modelMapper.map(permisoTracking, PermisoTrackingResponseDto.class);
    }

    public PermisoTracking toEntity(PermisoTrackingRequestDto permisoTrackingRequestDto){
        return modelMapper.map(permisoTrackingRequestDto, PermisoTracking.class);
    }


}
