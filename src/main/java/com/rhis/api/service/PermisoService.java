package com.rhis.api.service;

import com.rhis.api.dto.PermisoRequestDto;
import com.rhis.api.dto.PermisoResponseDto;
import com.rhis.api.exception.EmpleadoNotFoundException;
import com.rhis.api.exception.UnregisterVacationException;
import com.rhis.api.mapper.PermisoMapper;
import com.rhis.api.model.PermisoTracking;
import com.rhis.api.repository.EmpleadoRepository;
import com.rhis.api.repository.PermisoRepository;
import com.rhis.api.repository.PermisoTrackingRepository;
import com.rhis.api.repository.TipoPermisoRepository;
import com.rhis.api.utils.TokenUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisoService {

    private static final String ID_ENCARGADO_NOMINA = "c0b7e4b8-c4bc-463e-8847-3e4dc1fa3ba5";

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
    public PermisoResponseDto crearPermiso(PermisoRequestDto permisoRequestDto) throws UnregisterVacationException, EmpleadoNotFoundException {
        var empleado = empleadoRepository.findById(TokenUtils.getIdUserLogged())
                .orElseThrow(() -> new EmpleadoNotFoundException("Empleado logueado no existe"));
        var encargadoRrhh = empleadoRepository.findByPuestoIdPuesto(ID_ENCARGADO_NOMINA)
                .orElseThrow(UnregisterVacationException::new);
        var tipoPermiso = tipoPermisoRepository.findById(permisoRequestDto.getTipoPermiso());

        var permiso = permisoMapper.toEntity(permisoRequestDto);
        permiso.setEmpleado(empleado);

        var encargado = empleado.getPuesto().getDivision().getEncargado();
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

    /**
     * funcion que modifica un permiso
     *
     * @param permisoRequestDto
     * @return
     */
    public PermisoResponseDto modificarPermisos(PermisoRequestDto permisoRequestDto) {
        var permiso = permisoRepository.findById(permisoRequestDto.getIdPermiso())
                .orElseThrow();
        var tipoPermiso = tipoPermisoRepository.findById(permisoRequestDto.getTipoPermiso())
                .orElseThrow();

        permisoMapper.modificar(permisoRequestDto, permiso);
        permiso.setTipoPermiso(tipoPermiso);

        for (PermisoTracking tracking : permisoRequestDto.getPermisoTracking()) {
            for (PermisoTracking permisosTracking : permiso.getPermisoTracking()) {
                if (tracking.getIdPermisoTracking().equalsIgnoreCase(permisosTracking.getIdPermisoTracking())) {
                    permisosTracking.setEstado(tracking.isEstado());
                }
            }
        }
        return permisoMapper.toDto(permisoRepository.save(permiso));
    }
}
