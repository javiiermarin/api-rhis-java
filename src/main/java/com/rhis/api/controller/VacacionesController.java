package com.rhis.api.controller;

import com.rhis.api.dto.VacacionesRequestDto;
import com.rhis.api.dto.VacacionesResponseDto;
import com.rhis.api.service.VacacionesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rhis/vacaciones")
public class VacacionesController {

    private final VacacionesService vacacionesService;

    public VacacionesController(VacacionesService vacacionesService) {
        this.vacacionesService = vacacionesService;
    }

    @PostMapping
    public ResponseEntity<VacacionesResponseDto> registrarVacaciones(@RequestBody
                                                     @Valid
                                                     VacacionesRequestDto vacacionesRequestDto){
        var vacaciones = vacacionesService.registrarVacaciones(vacacionesRequestDto);
        return new ResponseEntity<>(vacaciones, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VacacionesResponseDto>> obtenerVacacionesPorDivision(
            @RequestParam(value = "idDivision") String idDivision
    ){
        return null;
    }
}
