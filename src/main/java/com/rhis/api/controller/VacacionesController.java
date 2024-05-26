package com.rhis.api.controller;

import com.rhis.api.dto.VacacionesRequestDto;
import com.rhis.api.dto.VacacionesResponseDto;
import com.rhis.api.service.VacacionesService;
import com.sun.xml.xsom.XSUnionSimpleType;
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
     * @param vacacionesRequestDto
     * @return
     */
    @PostMapping
    public ResponseEntity<VacacionesResponseDto> registrarVacaciones(@RequestBody
                                                     @Valid
                                                     VacacionesRequestDto vacacionesRequestDto){
        System.out.println(vacacionesRequestDto);
        var vacaciones = vacacionesService.registrarVacaciones(vacacionesRequestDto);
        System.out.println(vacacionesRequestDto);
        return new ResponseEntity<>(vacaciones, HttpStatus.CREATED);
    }

    /**
     * peticion que lista todas las solicitudes de vacaciones por usuario
     * @param idDivision
     * @return
     */
    @GetMapping
    public ResponseEntity<List<VacacionesResponseDto>> obtenerVacacionesPorDivision(
            @RequestParam(value = "idDivision") String idDivision
    ){
        var vacaciones = vacacionesService.obtenerVacacionesPorDivison(idDivision);
        return new ResponseEntity<>(vacaciones, HttpStatus.OK);
    }

    /**
     * Petidicon que modifica el estado de una solicitud de vacaciones
     * @param vacacionesRequestDto
     * @return
     */
    @PutMapping
    public ResponseEntity<VacacionesResponseDto> modificarVaciones(@RequestBody @Valid VacacionesRequestDto vacacionesRequestDto){
        var vacaciones = vacacionesService.modificarVacaiones(vacacionesRequestDto);
        return new ResponseEntity<>(vacaciones, HttpStatus.OK);
    }
}
