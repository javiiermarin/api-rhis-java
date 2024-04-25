package com.rhis.api.service;

import com.rhis.api.dto.EmpleadoRequestDto;
import com.rhis.api.dto.EmpleadoResponseDto;
import com.rhis.api.exception.PuestoNotFoundException;
import com.rhis.api.mapper.EmpleadoMapper;
import com.rhis.api.repository.EmpleadoRepository;
import com.rhis.api.repository.PuestoRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    private final EmpleadoMapper empleadoMapper;

    private final PuestoRespository puestoRespository;

    public EmpleadoService(EmpleadoRepository empleadoRepository, EmpleadoMapper empleadoMapper, PuestoRespository puestoRespository) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
        this.puestoRespository = puestoRespository;
    }

    /**
     * Funcion que crea un empleado
     * @param empleadoRequestDto
     * @return el empleado reado
     * @throws PuestoNotFoundException
     */
    public EmpleadoResponseDto crearEmpleado(EmpleadoRequestDto empleadoRequestDto)throws PuestoNotFoundException {
        var puesto = puestoRespository.findById(empleadoRequestDto.getPuesto())
                .orElseThrow(PuestoNotFoundException::new);

        var empleado = empleadoMapper.toEntity(empleadoRequestDto);
        empleado.setPuesto(puesto);
        empleado.setHabilitado(true);

        return empleadoMapper.toDto(empleadoRepository.save(empleado));
    }

    /**
     * Funcion que obtiene todos los empleados de un puesto especifico
     * @param idPuesto
     * @return
     */
    public List<EmpleadoResponseDto> obtenerEmpleadosPorPuesto(String idPuesto){
        return empleadoRepository.findAllByPuestoIdPuestoAndHabilitadoTrue(idPuesto)
                .stream()
                .map(empleadoMapper::toDto)
                .toList();
    }

}
