package com.rhis.api.controller;

import com.rhis.api.dto.DivisionRequestDto;
import com.rhis.api.dto.DivisionResponseDto;
import com.rhis.api.exception.DivisionNotFoundException;
import com.rhis.api.service.DivisionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rhis/divisiones")
public class DivisionController {

    private final DivisionService divisionService;

    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @GetMapping
    public ResponseEntity<List<DivisionResponseDto>> obtenerDivisiones() throws DivisionNotFoundException{
        var divisionesDto = divisionService.obtenerDivisiones();
        return new ResponseEntity<>(divisionesDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DivisionResponseDto> crearDivision(@RequestBody @Valid DivisionRequestDto divisionRequestDto){
        var division = divisionService.crearDivision(divisionRequestDto);
        return new ResponseEntity<>(division, HttpStatus.OK);
    }

    @PutMapping("/{idDivision}")
    public ResponseEntity<DivisionResponseDto> actualizarDivision(@PathVariable String idDivision,
                                                                 @RequestBody
                                                                 @Valid
                                                                 DivisionRequestDto divisionRequestDto) throws DivisionNotFoundException {
        var division = divisionService.actualizarDivision(idDivision, divisionRequestDto);
        return new ResponseEntity<>(division, HttpStatus.OK);
    }
}
