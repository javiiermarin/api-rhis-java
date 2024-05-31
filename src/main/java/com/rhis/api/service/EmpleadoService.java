package com.rhis.api.service;

import com.rhis.api.dto.EmpleadoReferenciaRequestDto;
import com.rhis.api.dto.EmpleadoRequestDto;
import com.rhis.api.dto.EmpleadoResponseDto;
import com.rhis.api.exception.PuestoNotFoundException;
import com.rhis.api.mapper.EmpleadoMapper;
import com.rhis.api.model.EmpleadoReferencia;
import com.rhis.api.repository.EmpleadoRepository;
import com.rhis.api.repository.PuestoRespository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EmptyStackException;
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
     *
     * @param empleadoRequestDto
     * @return el empleado reado
     * @throws PuestoNotFoundException
     */
    public EmpleadoResponseDto crearEmpleado(EmpleadoRequestDto empleadoRequestDto) throws PuestoNotFoundException {
        var puesto = puestoRespository.findById(empleadoRequestDto.getPuesto())
                .orElseThrow(PuestoNotFoundException::new);

        var empleado = empleadoMapper.toEntity(empleadoRequestDto);
        empleado.setPuesto(puesto);
        empleado.setHabilitado(true);

        return empleadoMapper.toDto(empleadoRepository.save(empleado));
    }

    /**
     * Funcion que obtiene todos los empleados de un puesto especifico || si no especificamos el puesto nos lista a todos los empleados
     *
     * @param idPuesto
     * @return empleados
     */
    public List<EmpleadoResponseDto> obtenerEmpleados(String idPuesto) {
        if (idPuesto != null) {
            return empleadoRepository.findAllByPuestoIdPuestoAndHabilitadoTrue(idPuesto)
                    .stream()
                    .map(empleadoMapper::toDto)
                    .toList();
        }

        return empleadoRepository.findAllByOrderByFechaIngresoAsc()
                .stream()
                .map(empleadoMapper::toDto)
                .toList();

    }

    public EmpleadoResponseDto getOneEmpleado(String idEmpleado) {
        var empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(EmptyStackException::new);

        return empleadoMapper.toDto(empleado);
    }

    /**
     * Funcion que modifica un empleado
     *
     * @param empleadoRequestDto
     * @return
     */
    public EmpleadoResponseDto editarEmpleado(EmpleadoRequestDto empleadoRequestDto) {
        var empleado = empleadoRepository.findById(empleadoRequestDto.getIdEmpleado()).orElseThrow();//Recueramos el empleado
        empleadoMapper.modificar(empleadoRequestDto, empleado);//seteamos los nuevos valores a ese empleado desde la clase mapper

        List<EmpleadoReferencia> nuevasReferencias = new ArrayList<>();//creamos una nueva lista de tipo EmpleadoReferencia

        // Recorrer la lista de referencias del request
        for (EmpleadoReferenciaRequestDto referenciaRequest : empleadoRequestDto.getEmpleadoReferencia()) {
            EmpleadoReferencia referenciaExistente = empleado.getEmpleadoReferencia().stream()
                    .filter(ref ->
                            ref.getIdEmpleadoReferencia().equalsIgnoreCase(referenciaRequest.getIdEmpleadoReferencia()))

                    .findFirst()
                    .orElse(null);

            if (referenciaExistente != null) {
                // Actualizar la referencia existente
                referenciaExistente.setNombres(referenciaRequest.getNombres());
                referenciaExistente.setApellidos(referenciaRequest.getApellidos());
                referenciaExistente.setTelefono(referenciaRequest.getTelefono());
                referenciaExistente.setDescripcion(referenciaRequest.getDescripcion());
            } else {
                // Crear una nueva referencia si no existe
                EmpleadoReferencia nuevaReferencia = new EmpleadoReferencia();
                nuevaReferencia.setNombres(referenciaRequest.getNombres());
                nuevaReferencia.setApellidos(referenciaRequest.getApellidos());
                nuevaReferencia.setTelefono(referenciaRequest.getTelefono());
                nuevaReferencia.setDescripcion(referenciaRequest.getDescripcion());

                nuevasReferencias.add(nuevaReferencia);
            }
        }

        empleado.getEmpleadoReferencia().addAll(nuevasReferencias);

        return empleadoMapper.toDto(empleadoRepository.save(empleado));
    }

}
