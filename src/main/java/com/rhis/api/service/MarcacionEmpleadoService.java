package com.rhis.api.service;

import com.rhis.api.dto.MarcacionEmpleadoRequestDto;
import com.rhis.api.dto.MarcacionEmpleadoResponseDto;
import com.rhis.api.mapper.MarcacionEmpleadoMapper;
import com.rhis.api.model.HoraExtra;
import com.rhis.api.model.MarcacionRandom;
import com.rhis.api.model.RangoTiempos;
import com.rhis.api.repository.EmpleadoRepository;
import com.rhis.api.repository.HoraExtraRepository;
import com.rhis.api.repository.MarcacionEmpleadoRepository;
import com.rhis.api.repository.MarcacionesRandomRepository;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Service
public class MarcacionEmpleadoService {

    private final MarcacionEmpleadoRepository marcacionEmpleadoRepository;
    private final MarcacionesRandomRepository marcacionesRandomRepository;
    private final EmpleadoRepository empleadoRepository;
    private final MarcacionEmpleadoMapper marcacionEmpleadoMapper;
    private final HoraExtraRepository horaExtraRepository;


    public MarcacionEmpleadoService(MarcacionEmpleadoRepository marcacionEmpleadoRepository, MarcacionesRandomRepository marcacionesRandomRepository,
                                    EmpleadoRepository empleadoRepository,
                                    MarcacionEmpleadoMapper marcacionEmpleadoMapper, HoraExtraRepository horaExtraRepository) {
        this.marcacionEmpleadoRepository = marcacionEmpleadoRepository;
        this.marcacionesRandomRepository = marcacionesRandomRepository;
        this.empleadoRepository = empleadoRepository;
        this.marcacionEmpleadoMapper = marcacionEmpleadoMapper;
        this.horaExtraRepository = horaExtraRepository;
    }

    /**
     * Metodo para registrar una marcacion utilizando el codigo del empleadeo
     *
     * @param marcacionEmpleadoRequestDto
     * @return
     */
    public void registrarMarcacion(MarcacionEmpleadoRequestDto
                                                                   marcacionEmpleadoRequestDto) {
        var empleado = empleadoRepository.findById(marcacionEmpleadoRequestDto.getEmpleado())
                .orElseThrow(NullPointerException::new);

        var marcacionEmpleado = marcacionEmpleadoMapper.toEntity(marcacionEmpleadoRequestDto);
        var marcacion = marcacionEmpleadoRepository.marcacionEmpleado(marcacionEmpleadoRequestDto.getEmpleado());

        MarcacionRandom marcacionRandom = new MarcacionRandom();

        RangoTiempos entrada = new RangoTiempos(LocalTime.of(6,0), LocalTime.of(9, 00));
        RangoTiempos salidaAlmuerzo = new RangoTiempos(LocalTime.of(12,0), LocalTime.of(13, 0));
        RangoTiempos entradaAlmuerzo = new RangoTiempos(LocalTime.of(12,45), LocalTime.of(13, 0));
        RangoTiempos salida = new RangoTiempos(LocalTime.of(16,30), LocalTime.of(18, 00));

        LocalTime ahora = LocalTime.now();
        if (entrada.contains(ahora)){
            marcacionEmpleado.setEmpleado(empleado);
            marcacionEmpleado.setHoraEntrada(ahora);
            marcacionEmpleadoRepository.save(marcacionEmpleado);
            return;
        }else if (marcacion != null && salidaAlmuerzo.contains(ahora)){
            marcacion.setHoraSalidaAlmuerzo(ahora);
            marcacionEmpleadoRepository.save(marcacion);
            return;
        }else if (marcacion != null && entradaAlmuerzo.contains(ahora)){
            marcacion.setHoraEntradaAlmuerzo(ahora);
            marcacionEmpleadoRepository.save(marcacion);
            return;
        }else if (marcacion != null && salida.contains(ahora)){
            marcacion.setHoraSalida(ahora);
            marcacionEmpleadoRepository.save(marcacion);
            return;
        }else {
            marcacionRandom.setHora(ahora);
            marcacionRandom.setEmpleado(empleado);
            //marcacionEmpleado.setMarcacionRandom(List.of(marcacionRandom));
            marcacionesRandomRepository.save(marcacionRandom);
        }

        if (marcacion != null){
            LocalTime entrada1 = marcacion.getHoraEntrada();
            LocalTime salida1 = marcacion.getHoraSalida();

            Duration duration = Duration.between(entrada1, salida1);
            long duractionReal = duration.toMinutes();

            if (duractionReal > 2){
                long horasExtras = duractionReal - 1;
                HoraExtra horaExtra = new HoraExtra();
                horaExtra.setHoraInicio(entrada1.plusHours(8));
                horaExtra.setHoraFinal(salida1);
                horaExtra.setEmpleado(empleado);
                horaExtra.setHoras(horasExtras);
                horaExtra.setHabilitad(false);

                horaExtraRepository.save(horaExtra);
            }

        }
    }

    /**
     * Metodo que nos devulve una lista de las marcaciones de un empleado especifico o de todos los empleados
     * @param idEmpleado
     * @return
     */
    public List<MarcacionEmpleadoResponseDto> marcacionesPorEmpleado(String idEmpleado){

        if (idEmpleado == null){
            return marcacionEmpleadoRepository.findAll()
                    .stream()
                    .map(marcacionEmpleadoMapper::toDto)
                    .toList();
        }

        return marcacionEmpleadoRepository.findById(idEmpleado)
                .stream()
                .map(marcacionEmpleadoMapper::toDto)
                .toList();

    }




}
