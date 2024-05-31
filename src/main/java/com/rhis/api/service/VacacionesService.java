package com.rhis.api.service;

import com.rhis.api.dto.VacacionesRequestDto;
import com.rhis.api.dto.VacacionesResponseDto;
import com.rhis.api.exception.EmpleadoNotFoundException;
import com.rhis.api.exception.UnregisterVacationException;
import com.rhis.api.mapper.VacacionesMapper;
import com.rhis.api.model.VacacionesTracking;
import com.rhis.api.repository.EmpleadoRepository;
import com.rhis.api.repository.VacacionesRepository;
import com.rhis.api.utils.TokenUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacacionesService {

    private static final String ID_ENCARGADO_NOMINA = "c0b7e4b8-c4bc-463e-8847-3e4dc1fa3ba5";

    private final VacacionesRepository vacacionesRepository;
    private final EmpleadoRepository empleadoRepository;
    private final VacacionesMapper vacacionesMapper;

    public VacacionesService(VacacionesRepository vacacionesRepository, EmpleadoRepository empleadoRepository, VacacionesMapper vacacionesMapper) {
        this.vacacionesRepository = vacacionesRepository;
        this.empleadoRepository = empleadoRepository;
        this.vacacionesMapper = vacacionesMapper;
    }

    /**
     * metodo para registrar vacaciones
     *
     * @param vacacionesRequestDto
     * @return
     */
    public VacacionesResponseDto registrarVacaciones(VacacionesRequestDto vacacionesRequestDto) throws EmpleadoNotFoundException, UnregisterVacationException {
        var empleado = empleadoRepository.findById(TokenUtils.getIdUserLogged())
                .orElseThrow(() -> new EmpleadoNotFoundException("Empleado logueado no existe"));
        var encargadoDivision = empleado.getPuesto().getDivision().getEncargado();

        var encargadoRrhh = empleadoRepository.findByPuestoIdPuesto(ID_ENCARGADO_NOMINA)
                .orElseThrow(UnregisterVacationException::new);

        var vacaciones = vacacionesMapper.toEntity(vacacionesRequestDto);
        vacaciones.setEmpleado(empleado);

        List<VacacionesTracking> trackingList = new ArrayList<>();

        if (encargadoDivision != null) {
            VacacionesTracking vacacionesTracking1 = new VacacionesTracking();
            vacacionesTracking1.setVacaciones(vacaciones);
            vacacionesTracking1.setEmpleado(encargadoDivision);
            vacacionesTracking1.setEstado(false);
            trackingList.add(vacacionesTracking1);
        }

        VacacionesTracking vacacionesTracking2 = new VacacionesTracking();
        vacacionesTracking2.setVacaciones(vacaciones);
        vacacionesTracking2.setEmpleado(encargadoRrhh);
        vacacionesTracking2.setEstado(false);
        trackingList.add(vacacionesTracking2);

        vacaciones.setVacacionesTracking(trackingList);

        return vacacionesMapper.toDto(vacacionesRepository.save(vacaciones));

    }

    /**
     * metodo para listar las vacaciones por division
     *
     * @return
     */
    public List<VacacionesResponseDto> getVacations() {
        if (TokenUtils.isRrhh()) {
            return vacacionesRepository.findAll()
                    .stream()
                    .map(vacacionesMapper::toDto)
                    .toList();
        }

        return vacacionesRepository.findVacacionesByEmpleadoAndTrackingEmpleadoOrderByCreatedAtDesc(TokenUtils.getIdUserLogged(), TokenUtils.getIdUserLogged())
                .stream()
                .map(vacacionesMapper::toDto)
                .toList();
    }


    /**
     * Funcion que modifica el estado de una solicitud de vacaciones
     *
     * @param vacacionesRequestDto
     * @return
     */
    public VacacionesResponseDto modificarVacaiones(VacacionesRequestDto vacacionesRequestDto) {
        var vacaciones = vacacionesRepository.findById(vacacionesRequestDto.getIdVacaciones())
                .orElseThrow();

        vacacionesMapper.modificar(vacacionesRequestDto, vacaciones);

        for (VacacionesTracking tracking : vacacionesRequestDto.getVacacionesTracking()) {
            for (VacacionesTracking vacacionesTracking : vacaciones.getVacacionesTracking()) {
                if (tracking.getIdVacacionesTracking().equalsIgnoreCase(vacacionesTracking.getIdVacacionesTracking())) {
                    vacacionesTracking.setEstado(tracking.isEstado());
                }
            }
        }
        return vacacionesMapper.toDto(vacacionesRepository.save(vacaciones));
    }


}
