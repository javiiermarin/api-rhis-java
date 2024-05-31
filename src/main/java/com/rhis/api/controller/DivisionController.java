package com.rhis.api.controller;

import com.rhis.api.dto.DivisionRequestDto;
import com.rhis.api.dto.DivisionResponseDto;
import com.rhis.api.exception.DivisionNotFoundException;
import com.rhis.api.exception.EmpleadoNotFoundException;
import com.rhis.api.service.DivisionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/rhis/divisiones")
public class DivisionController {

    private final DivisionService divisionService;

    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    /**
     * peticion que obtiene una lista de diviones
     *
     * @return
     * @throws DivisionNotFoundException
     */
    @GetMapping
    public ResponseEntity<List<DivisionResponseDto>> obtenerDivisiones() {
        var divisionesDto = divisionService.obtenerDivisiones();
        return new ResponseEntity<>(divisionesDto, HttpStatus.OK);
    }

    /**
     * Peticion que crea una division
     *
     * @param divisionRequestDto
     * @return
     */
    @PostMapping
    public ResponseEntity<DivisionResponseDto> crearDivision(@RequestBody @Valid DivisionRequestDto divisionRequestDto) throws EmpleadoNotFoundException {
        var division = divisionService.crearDivision(divisionRequestDto);
        return new ResponseEntity<>(division, HttpStatus.OK);
    }

    /**
     * peticion que modifica una division
     *
     * @param divisionRequestDto
     * @return
     * @throws DivisionNotFoundException
     */
    @PutMapping
    public ResponseEntity<DivisionResponseDto> actualizarDivision(
            @RequestBody
            @Valid
            DivisionRequestDto divisionRequestDto) throws DivisionNotFoundException, EmpleadoNotFoundException {
        var division = divisionService.actualizarDivision(divisionRequestDto);
        return new ResponseEntity<>(division, HttpStatus.OK);
    }
}
