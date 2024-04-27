package com.rhis.api.mapper;

import com.rhis.api.dto.TipoPermisoRequestDto;
import com.rhis.api.dto.TipoPermisoResposeDto;
import com.rhis.api.model.TipoPermiso;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TipoPermisoMapper {

    private final ModelMapper modelMapper;

    public TipoPermisoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TipoPermisoResposeDto toDto(TipoPermiso tipoPermiso){
        return modelMapper.map(tipoPermiso, TipoPermisoResposeDto.class);
    }

    public TipoPermiso toEntity(TipoPermisoRequestDto tipoPermisoRequestDto){
        return modelMapper.map(tipoPermisoRequestDto, TipoPermiso.class);
    }

}
