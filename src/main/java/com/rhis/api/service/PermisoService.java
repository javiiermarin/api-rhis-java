package com.rhis.api.service;

import com.rhis.api.dto.PermisoRequestDto;
import com.rhis.api.dto.PermisoResponseDto;
import com.rhis.api.mapper.PermisoMapper;
import com.rhis.api.model.PermisoTracking;
import com.rhis.api.repository.EmpleadoRepository;
import com.rhis.api.repository.PermisoRepository;
import com.rhis.api.repository.PermisoTrackingRepository;
import com.rhis.api.repository.TipoPermisoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisoService {

    private final PermisoRepository permisoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final PermisoMapper permisoMapper;
    private final TipoPermisoRepository tipoPermisoRepository;
    private final PermisoTrackingRepository permisoTrackingRepository;

    public PermisoService(PermisoRepository permisoRepository, EmpleadoRepository empleadoRepository, PermisoMapper permisoMapper, TipoPermisoRepository tipoPermisoRepository, PermisoTrackingRepository permisoTrackingRepository) {
        this.permisoRepository = permisoRepository;
        this.empleadoRepository = empleadoRepository;
        this.permisoMapper = permisoMapper;
        this.tipoPermisoRepository = tipoPermisoRepository;
        this.permisoTrackingRepository = permisoTrackingRepository;
    }

    /**
     * metodo para crear un permiso a partir de los parametros solicitados
     *
     * @param permisoRequestDto
     * @return
     */

    public PermisoResponseDto crearPermiso(PermisoRequestDto permisoRequestDto) {
        var empleado = empleadoRepository.findById(permisoRequestDto.getEmpleado());
        var encargadoRrhh = empleadoRepository.findByPuestoIdPuesto("c0b7e4b8-c4bc-463e-8847-3e4dc1fa3ba5");
        var tipoPermiso = tipoPermisoRepository.findById(permisoRequestDto.getTipoPermiso());

        var permiso = permisoMapper.toEntity(permisoRequestDto);
        permiso.setEmpleado(empleado.get());
        var encargado = empleado.get().getPuesto().getDivision().getEncargado();
        permiso.setTipoPermiso(tipoPermiso.get());


        PermisoTracking permisoTracking1 = new PermisoTracking();
        permisoTracking1.setPermiso(permiso);
        permisoTracking1.setEmpleado(encargado);
        permisoTracking1.setEstado(false);

        PermisoTracking permisoTracking2 = new PermisoTracking();
        permisoTracking2.setEmpleado(encargadoRrhh);
        permisoTracking2.setPermiso(permiso);
        permisoTracking2.setEstado(false);

        permiso.setPermisoTracking(List.of(permisoTracking1, permisoTracking2));

        return permisoMapper.toDto(permisoRepository.save(permiso));
    }

    /**
     * metodo que lista los permisos de un usuario espeficado
     *
     * @param idEmpleado
     * @return permisos del empleado
     */
    public List<PermisoResponseDto> listarPermisosPorEmpleado(String idEmpleado) {
        return permisoRepository.findAllByEmpleadoIdEmpleado(idEmpleado)
                .stream()
                .map(permisoMapper::toDto)
                .toList();
    }
}
