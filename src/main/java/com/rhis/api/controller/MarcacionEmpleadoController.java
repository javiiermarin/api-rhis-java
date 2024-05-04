package com.rhis.api.controller;

import com.rhis.api.dto.MarcacionEmpleadoRequestDto;
import com.rhis.api.dto.MarcacionEmpleadoResponseDto;
import com.rhis.api.service.MarcacionEmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rhis/marcaciones")
public class MarcacionEmpleadoController {

    private final MarcacionEmpleadoService marcacionEmpleadoService;

    public MarcacionEmpleadoController(MarcacionEmpleadoService marcacionEmpleadoService) {
        this.marcacionEmpleadoService = marcacionEmpleadoService;
    }

    @PostMapping
    public ResponseEntity<MarcacionEmpleadoResponseDto> registrarMarcacion(
            @RequestBody @Valid MarcacionEmpleadoRequestDto marcacionEmpleadoRequestDto){
        var marcacionEmpleado = marcacionEmpleadoService.registrarMarcacion(marcacionEmpleadoRequestDto);
        return new ResponseEntity<>(marcacionEmpleado, HttpStatus.CREATED);
    }
}
