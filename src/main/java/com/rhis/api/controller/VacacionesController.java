package com.rhis.api.controller;

import com.rhis.api.dto.VacacionesRequestDto;
import com.rhis.api.dto.VacacionesResponseDto;
import com.rhis.api.exception.EmpleadoNotFoundException;
import com.rhis.api.exception.UnregisterVacationException;
import com.rhis.api.service.VacacionesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rhis/vacaciones")
public class VacacionesController {

    private final VacacionesService vacacionesService;

    public VacacionesController(VacacionesService vacacionesService) {
        this.vacacionesService = vacacionesService;
    }

    /**
     * peticion que genera una solicitud de vacaciones
     *
     * @param vacacionesRequestDto
     * @return
     */
    @PostMapping
    public ResponseEntity<VacacionesResponseDto> registrarVacaciones(@RequestBody
                                                                     @Valid
                                                                     VacacionesRequestDto vacacionesRequestDto) throws EmpleadoNotFoundException, UnregisterVacationException {
        var vacaciones = vacacionesService.registrarVacaciones(vacacionesRequestDto);
        return new ResponseEntity<>(vacaciones, HttpStatus.CREATED);
    }

    /**
     * peticion que lista todas las solicitudes de vacaciones por usuario
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<VacacionesResponseDto>> obtenerVacacionesPorDivision(
    ) {
        var vacaciones = vacacionesService.getVacations();
        return new ResponseEntity<>(vacaciones, HttpStatus.OK);
    }

    /**
     * Petidicon que modifica el estado de una solicitud de vacaciones
     *
     * @param vacacionesRequestDto
     * @return
     */
    @PutMapping
    public ResponseEntity<VacacionesResponseDto> modificarVaciones(@RequestBody @Valid VacacionesRequestDto vacacionesRequestDto) {
        var vacaciones = vacacionesService.modificarVacaiones(vacacionesRequestDto);
        return new ResponseEntity<>(vacaciones, HttpStatus.OK);
    }
}
