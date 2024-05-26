package com.rhis.api.service;

import com.rhis.api.dto.HoraExtraRequestDto;
import com.rhis.api.dto.HoraExtraResponseDto;
import com.rhis.api.mapper.HoraExtraMapper;
import com.rhis.api.model.HoraExtra;
import com.rhis.api.repository.EmpleadoRepository;
import com.rhis.api.repository.HoraExtraRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HoraExtraService {

    private final HoraExtraRepository horaExtraRepository;
    private final EmpleadoRepository empleadoRepository;
    private final HoraExtraMapper horaExtraMapper;

    public HoraExtraService(HoraExtraRepository horaExtraRepository, EmpleadoRepository empleadoRepository, HoraExtraMapper horaExtraMapper) {
        this.horaExtraRepository = horaExtraRepository;
        this.empleadoRepository = empleadoRepository;
        this.horaExtraMapper = horaExtraMapper;
    }

    /**
     * metodo que nos lista todas las horas extras de los empleados
     * @return
     */
    public List<HoraExtraResponseDto> mostrarHorasExtras(){
        List<HoraExtraResponseDto> horasExtras = horaExtraRepository.findAll()
                .stream()
                .map(horaExtraMapper::toDto)
                .toList();

        return horasExtras;
    }

}
