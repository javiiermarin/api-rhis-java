package com.rhis.api.controller;

import com.rhis.api.dto.MarcacionRandomResponseDto;
import com.rhis.api.service.MarcacionRandomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rhis/marcacionesRandom")
public class MarcacionRandomController {

    private final MarcacionRandomService marcacionRandomService;

    public MarcacionRandomController(MarcacionRandomService marcacionRandomService) {
        this.marcacionRandomService = marcacionRandomService;
    }

    @GetMapping
    public ResponseEntity<List<MarcacionRandomResponseDto>> getMarcacionRandom() {
        var marcacionesRandom = marcacionRandomService.obtenerMarcacionRandom();

        return new ResponseEntity<>(marcacionesRandom, HttpStatus.OK);
    }
}
