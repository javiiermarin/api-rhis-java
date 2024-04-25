package com.rhis.api.controller;

import com.rhis.api.dto.EmpleadoRequestDto;
import com.rhis.api.dto.EmpleadoResponseDto;
import com.rhis.api.exception.PuestoNotFoundException;
import com.rhis.api.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rhis/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponseDto> crearEmpleado(@RequestBody @Valid EmpleadoRequestDto empleadoRequestDto)
            throws PuestoNotFoundException {
        var empleado = empleadoService.crearEmpleado(empleadoRequestDto);

        return new ResponseEntity<>(empleado, HttpStatus.CREATED);
    }

    @GetMapping("/puestos")
    public ResponseEntity<List<EmpleadoResponseDto>> obtenerEmpleadosPorPuesto(
            @RequestParam(value = "idPuesto") String idPuesto
    )throws PuestoNotFoundException{
        var empleadosDto = empleadoService.obtenerEmpleadosPorPuesto(idPuesto);
        return new ResponseEntity<>(empleadosDto, HttpStatus.OK);
    }
}
