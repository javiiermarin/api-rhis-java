package com.rhis.api.controller;

import com.rhis.api.dto.JornadaRequestDto;
import com.rhis.api.dto.JornadaResponseDto;
import com.rhis.api.service.JornadaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/rhis/jornadas")
public class JornadaController {

    private final JornadaService jornadaService;

    public JornadaController(JornadaService jornadaService) {
        this.jornadaService = jornadaService;
    }

    /**
     * peticion que crea una jornada
     *
     * @param jornadaRequestDto
     * @return
     */
    @PostMapping
    public ResponseEntity<JornadaResponseDto> crearJornada(@RequestBody @Valid JornadaRequestDto jornadaRequestDto){
        var jornada = jornadaService.createJornada(jornadaRequestDto);
        return new ResponseEntity<>(jornada, HttpStatus.CREATED);
    }

    /**
     * peticion que lista todas las jornadas
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<JornadaResponseDto>> obtenerJornadas(){
        var jornadas = jornadaService.listarJornadas();
        return new ResponseEntity<>(jornadas, HttpStatus.OK);
    }


    /**
     * Peticion que modifica una jornada
     *
     * @param jornadaRequestDto
     * @return
     */
    @PutMapping
    public ResponseEntity<JornadaResponseDto> modificarJornada(@RequestBody @Valid JornadaRequestDto jornadaRequestDto){
        var jornada = jornadaService.modificarJornada(jornadaRequestDto);
        return new ResponseEntity<>(jornada, HttpStatus.OK);
    }


}
