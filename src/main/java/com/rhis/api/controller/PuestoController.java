package com.rhis.api.controller;

import com.rhis.api.dto.PuestoRequestDto;
import com.rhis.api.dto.PuestoResponseDto;
import com.rhis.api.exception.DivisionNotFoundException;
import com.rhis.api.service.PuestoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rhis/puestos")
public class PuestoController {

    private final PuestoService puestoService;

    public PuestoController(PuestoService puestoService) {
        this.puestoService = puestoService;
    }

    @PostMapping
    public ResponseEntity<PuestoResponseDto> crearPuesto(@RequestBody @Valid PuestoRequestDto puestoRequestDto){
        var puesto = puestoService.crearPuesto(puestoRequestDto);
        return new ResponseEntity<>(puesto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PuestoResponseDto>> obtenerPuestosPorDivision(
            @RequestParam(value = "idDivision", required = false) String idDivision
    )throws DivisionNotFoundException {
        var puestoDto = puestoService.obtenerPuestosPorDivision(idDivision);
        return new ResponseEntity<>(puestoDto, HttpStatus.OK);
    }
}
