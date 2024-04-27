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

    public List<PermisoTrackingResponseDto> obtenerTracking(boolean estado){

        return permisoTrackingRepository.findAllByEstado(estado)
                .stream()
                .map(permisoTrackingMapper::toDto)
                .toList();
    }

    public PermisoTrackingResponseDto actualizarTracking(String idPermisoTracking,
                                                         PermisoTrackingRequestDto permisoTrackingRequestDto){
        var tracking = permisoTrackingRepository.findById(idPermisoTracking);
        tracking.get().setEstado(permisoTrackingRequestDto.getEstado());
        return permisoTrackingMapper.toDto(permisoTrackingRepository.save(tracking.get()));

    }

}
