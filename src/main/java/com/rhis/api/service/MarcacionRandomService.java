package com.rhis.api.service;

import com.rhis.api.dto.MarcacionRandomResponseDto;
import com.rhis.api.mapper.MarcacionRandomMapper;
import com.rhis.api.repository.MarcacionesRandomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcacionRandomService {

    private final MarcacionesRandomRepository marcacionesRandomRepository;
    private final MarcacionRandomMapper marcacionRandomMapper;


    public MarcacionRandomService(MarcacionesRandomRepository marcacionesRandomRepository, MarcacionRandomMapper marcacionRandomMapper) {
        this.marcacionesRandomRepository = marcacionesRandomRepository;
        this.marcacionRandomMapper = marcacionRandomMapper;
    }

    /**
     * funcion que lista todas las marcaciones random
     * @return
     */
    public List<MarcacionRandomResponseDto> obtenerMarcacionRandom() {
        var marcacionRandom = marcacionesRandomRepository.findAll()
                .stream()
                .map(marcacionRandomMapper::toDto)
                .toList();

        return marcacionRandom;
    }
}
