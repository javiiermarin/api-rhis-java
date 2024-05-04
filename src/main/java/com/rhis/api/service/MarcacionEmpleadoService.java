package com.rhis.api.service;

import com.rhis.api.dto.MarcacionEmpleadoRequestDto;
import com.rhis.api.dto.MarcacionEmpleadoResponseDto;
import com.rhis.api.mapper.MarcacionEmpleadoMapper;
import com.rhis.api.repository.EmpleadoRepository;
import com.rhis.api.repository.MarcacionEmpleadoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class MarcacionEmpleadoService {

    private final MarcacionEmpleadoRepository marcacionEmpleadoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final MarcacionEmpleadoMapper marcacionEmpleadoMapper;


    public MarcacionEmpleadoService(MarcacionEmpleadoRepository marcacionEmpleadoRepository, EmpleadoRepository empleadoRepository, MarcacionEmpleadoMapper marcacionEmpleadoMapper) {
        this.marcacionEmpleadoRepository = marcacionEmpleadoRepository;
        this.empleadoRepository = empleadoRepository;
        this.marcacionEmpleadoMapper = marcacionEmpleadoMapper;
    }

    public MarcacionEmpleadoResponseDto registrarMarcacion(MarcacionEmpleadoRequestDto marcacionEmpleadoRequestDto){
        var empleado = empleadoRepository.findById(marcacionEmpleadoRequestDto.getEmpleado())
                .orElseThrow(NullPointerException::new);

        LocalTime horaEntrada = LocalTime.of(6,00);
        LocalTime horaEntrada2 = horaEntrada.plusHours(2);
        LocalTime salidaAlmuerzo = LocalTime.of(12,00);
        LocalTime salidaAlmuerzo2 = LocalTime.of(12,15);
        LocalTime entradaAlmuerzo = LocalTime.of(12,45);
        LocalTime entradaAlmuerzo2 = LocalTime.of(13,00);
        LocalTime salida = LocalTime.of(16,30);
        LocalTime salida2 = LocalTime.of(18,30);

        var marcacion = marcacionEmpleadoRepository.idMarcacionEmpleado(marcacionEmpleadoRequestDto.getEmpleado());

        if (LocalTime.now().isAfter(horaEntrada) && LocalTime.now().isBefore(horaEntrada2)){
            marcacion.setEmpleado(empleado);
            marcacion.setHoraEntrada(LocalTime.now());
        } else if (LocalTime.now().isAfter(salidaAlmuerzo) && LocalTime.now().isBefore(salidaAlmuerzo2)) {
            marcacion.setEmpleado(empleado);
            marcacion.setHoraSalidaAlmuerzo(LocalTime.now());
        }else if (LocalTime.now().isAfter(entradaAlmuerzo) && LocalTime.now().isBefore(entradaAlmuerzo2)) {
            marcacion.setEmpleado(empleado);
            marcacion.setHoraEntradaAlmuerzo(LocalTime.now());
        } else if (LocalTime.now().isAfter(salida) && LocalTime.now().isBefore(salida2)) {
            marcacion.setHoraSalida(LocalTime.now());
            marcacion.setFecha(LocalDate.now());
        }

        return marcacionEmpleadoMapper.toDto(marcacionEmpleadoRepository.save(marcacion));
    }

}
