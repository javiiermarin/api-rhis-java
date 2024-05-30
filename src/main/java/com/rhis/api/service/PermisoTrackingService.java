package com.rhis.api.service;

import com.rhis.api.dto.PermisoTrackingRequestDto;
import com.rhis.api.dto.PermisoTrackingResponseDto;
import com.rhis.api.enums.EstadoPermiso;
import com.rhis.api.mapper.PermisoTrackingMapper;
import com.rhis.api.repository.PermisoTrackingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisoTrackingService {

    private final PermisoTrackingRepository permisoTrackingRepository;
    private final PermisoTrackingMapper permisoTrackingMapper;

    public PermisoTrackingService(PermisoTrackingRepository permisoTrackingRepository, PermisoTrackingMapper permisoTrackingMapper) {
        this.permisoTrackingRepository = permisoTrackingRepository;
        this.permisoTrackingMapper = permisoTrackingMapper;
    }


    /**
     * Funcion que lista todos los estados dependiendo de un estado
     *
     * @param estado
     * @return
     */
    public List<PermisoTrackingResponseDto> obtenerTracking(boolean estado){

        return permisoTrackingRepository.findAllByEstado(estado)
                .stream()
                .map(permisoTrackingMapper::toDto)
                .toList();
    }


    /**
     * funcion que modifica el estado de un permiso
     *
     * @param idPermisoTracking
     * @param permisoTrackingRequestDto
     * @return
     */
    public PermisoTrackingResponseDto actualizarTracking(String idPermisoTracking,
                                                         PermisoTrackingRequestDto permisoTrackingRequestDto){
        var tracking = permisoTrackingRepository.findById(idPermisoTracking);
        tracking.get().setEstado(permisoTrackingRequestDto.getEstado());
        return permisoTrackingMapper.toDto(permisoTrackingRepository.save(tracking.get()));

    }

}
