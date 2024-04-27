package com.rhis.api.service;

import com.rhis.api.dto.TipoPermisoRequestDto;
import com.rhis.api.dto.TipoPermisoResposeDto;
import com.rhis.api.mapper.TipoPermisoMapper;
import com.rhis.api.repository.TipoPermisoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPermisoService {

    private final TipoPermisoRepository tipoPermisoRepository;
    private final TipoPermisoMapper tipoPermisoMapper;


    public TipoPermisoService(TipoPermisoRepository tipoPermisoRepository, TipoPermisoMapper tipoPermisoMapper) {
        this.tipoPermisoRepository = tipoPermisoRepository;
        this.tipoPermisoMapper = tipoPermisoMapper;
    }

    /**
     * Metodo para crear un nuevo tipo de permiso
     * @param tipoPermisoRequestDto
     * @return
     */
    public TipoPermisoResposeDto crearTipoPermiso(TipoPermisoRequestDto tipoPermisoRequestDto){
        var tipoPermiso = tipoPermisoMapper.toEntity(tipoPermisoRequestDto);
        tipoPermiso.setIsEnbaled(true);
        return tipoPermisoMapper.toDto(tipoPermisoRepository.save(tipoPermiso));
    }

    /**
     * metodo para listar todos los tipos de permiso disponibles
     * @return
     */
    public List<TipoPermisoResposeDto> obtenerTipoPermiso(){
        return tipoPermisoRepository.findAll()
                .stream()
                .map(tipoPermisoMapper::toDto)
                .toList();
    }
}
