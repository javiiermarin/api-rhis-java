package com.rhis.api.service;

import com.rhis.api.dto.HoraExtraRequestDto;
import com.rhis.api.dto.HoraExtraResponseDto;
import com.rhis.api.mapper.HoraExtraMapper;
import com.rhis.api.repository.EmpleadoRepository;
import com.rhis.api.repository.HoraExtraRepository;
import org.springframework.stereotype.Service;


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
     * metodo para registrar horas extras si se cumple con los parametros establecidos
     * @param horaExtraRequestDto
     * @return
     */

    public HoraExtraResponseDto registrarHoraExtra(HoraExtraRequestDto horaExtraRequestDto){
        var horaExtra = horaExtraMapper.toEntity(horaExtraRequestDto);

        return null;

    }




}
