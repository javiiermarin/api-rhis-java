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

@CrossOrigin(origins = "*")
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

    @GetMapping
    public ResponseEntity<List<EmpleadoResponseDto>> obtenerEmpleados(
            @RequestParam(value = "idPuesto", required = false) String idPuesto
    ) {
        var empleadosDto = empleadoService.obtenerEmpleados(idPuesto);
        return new ResponseEntity<>(empleadosDto, HttpStatus.OK);
    }

    @GetMapping("{idEmpleado}")
    public ResponseEntity<EmpleadoResponseDto> getOneEmpleado(
            @PathVariable(value = "idEmpleado") String idEmpleado
    ) {
        var empleadosDto = empleadoService.getOneEmpleado(idEmpleado);
        return new ResponseEntity<>(empleadosDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<EmpleadoResponseDto> modificarEmpleado(@RequestBody
                                                                 @Valid EmpleadoRequestDto empleadoRequestDto) {
        var empleado = empleadoService.editarEmpleado(empleadoRequestDto);
        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }
}
