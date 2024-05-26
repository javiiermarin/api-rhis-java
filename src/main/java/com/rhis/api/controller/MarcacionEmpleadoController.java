package com.rhis.api.controller;

import com.rhis.api.dto.MarcacionEmpleadoRequestDto;
import com.rhis.api.dto.MarcacionEmpleadoResponseDto;
import com.rhis.api.service.MarcacionEmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rhis/marcaciones")
public class MarcacionEmpleadoController {

    private final MarcacionEmpleadoService marcacionEmpleadoService;

    public MarcacionEmpleadoController(MarcacionEmpleadoService marcacionEmpleadoService) {
        this.marcacionEmpleadoService = marcacionEmpleadoService;
    }

    /**
     * peticion que genera una marcacion de un empleado a aprtir de su codigo de empleado
     *
     * @param marcacionEmpleadoRequestDto
     * @return
     */
    @PostMapping
    public ResponseEntity<MarcacionEmpleadoResponseDto> registrarMarcacion(
            @RequestBody @Valid MarcacionEmpleadoRequestDto marcacionEmpleadoRequestDto){
            marcacionEmpleadoService.registrarMarcacion(marcacionEmpleadoRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<MarcacionEmpleadoResponseDto>> listarMarcaciones(
            @RequestParam(value = "idEmpleado" , required = false) String idEmpleado){
        var marcaciones = marcacionEmpleadoService.marcacionesPorEmpleado(idEmpleado);
        return new ResponseEntity<>(marcaciones, HttpStatus.OK);
    }


}
