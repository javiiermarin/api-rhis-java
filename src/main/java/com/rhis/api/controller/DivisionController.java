package com.rhis.api.controller;

import com.rhis.api.dto.DivisionResponseDto;
import com.rhis.api.exception.DivisionNotFoundException;
import com.rhis.api.service.DivisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/divisiones")
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
}
