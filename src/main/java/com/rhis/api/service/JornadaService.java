package com.rhis.api.service;

import com.rhis.api.dto.JornadaRequestDto;
import com.rhis.api.dto.JornadaResponseDto;
import com.rhis.api.mapper.JornadaMapper;
import com.rhis.api.repository.JornadaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * Funcion que devuelve todas las jornadas
     *
     * @return
     */
    public List<JornadaResponseDto> listarJornadas(){
        return jornadaRepository.findAll()
                .stream()
                .map(jornadaMapper::toDto)
                .toList();
    }

    /**
     * Funcion que modifica una jornada
     *
     * @param jornadaRequestDto
     * @return
     */

    public JornadaResponseDto modificarJornada(JornadaRequestDto jornadaRequestDto){
        var jornada = jornadaRepository.findById(jornadaRequestDto.getIdJornada()).orElseThrow();

        jornada.setHoraInicio(jornadaRequestDto.getHoraInicio());
        jornada.setHoraFin(jornadaRequestDto.getHoraFin());
        jornada.setValor(jornadaRequestDto.getValor());

        return jornadaMapper.toDto(jornadaRepository.save(jornada));

    }
}
