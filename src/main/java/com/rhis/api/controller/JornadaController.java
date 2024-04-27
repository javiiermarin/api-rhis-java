package com.rhis.api.controller;

import com.rhis.api.dto.JornadaRequestDto;
import com.rhis.api.dto.JornadaResponseDto;
import com.rhis.api.service.JornadaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rhis/jornadas")
public class JornadaController {

    private final JornadaService jornadaService;

    public JornadaController(JornadaService jornadaService) {
        this.jornadaService = jornadaService;
    }

    @PostMapping
    public ResponseEntity<JornadaResponseDto> crearJornada(@RequestBody @Valid JornadaRequestDto jornadaRequestDto){
        var jornada = jornadaService.createJornada(jornadaRequestDto);
        return new ResponseEntity<>(jornada, HttpStatus.CREATED);
    }
}
