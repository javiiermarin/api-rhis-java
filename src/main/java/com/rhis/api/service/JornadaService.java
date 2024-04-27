package com.rhis.api.service;

import com.rhis.api.dto.JornadaRequestDto;
import com.rhis.api.dto.JornadaResponseDto;
import com.rhis.api.mapper.JornadaMapper;
import com.rhis.api.repository.JornadaRepository;
import org.springframework.stereotype.Service;

@Service
public class JornadaService {

    private final JornadaRepository jornadaRepository;
    private final JornadaMapper jornadaMapper;

    public JornadaService(JornadaRepository jornadaRepository, JornadaMapper jornadaMapper) {
        this.jornadaRepository = jornadaRepository;
        this.jornadaMapper = jornadaMapper;
    }

    /**
     * metodo para registrar jornadas
     * @param jornadaRequestDto
     * @return
     */
    public JornadaResponseDto createJornada(JornadaRequestDto jornadaRequestDto){
        var jornada = jornadaMapper.toEntity(jornadaRequestDto);

        return jornadaMapper.toDto(jornadaRepository.save(jornada));
    }
}
