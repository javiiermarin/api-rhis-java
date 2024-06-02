package com.rhis.api.controller;

import com.rhis.api.dto.PagoPlanillaRequestDto;
import com.rhis.api.dto.PagoPlanillaResponseDto;
import com.rhis.api.service.PagoPlanillaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/rhis/pago_planillas")
public class PagoPlanillaController {

    private final PagoPlanillaService pagoPlanillaService;

    public PagoPlanillaController(PagoPlanillaService pagoPlanillaService) {
        this.pagoPlanillaService = pagoPlanillaService;
    }

    /**
     * Peticion que general la planilla de todos los usuarios en un periodo de fechas
     * @param pagoPlanillaRequestDto
     * @return
     */
    @PostMapping
    public ResponseEntity<PagoPlanillaResponseDto> generarPlanilla(
            @RequestBody @Valid PagoPlanillaRequestDto pagoPlanillaRequestDto){
        var pagoPlanilla = pagoPlanillaService.generarPlanilla(pagoPlanillaRequestDto);

        return new ResponseEntity<>(pagoPlanilla, HttpStatus.CREATED);
    }


    /**
     * Peticion que lista todas las planillas
     * @return
     */
    @GetMapping
    public ResponseEntity<List<PagoPlanillaResponseDto>> listarPlanillas() {
        var planilla = pagoPlanillaService.obtenerPlanillas();
        return new ResponseEntity<>(planilla, HttpStatus.OK);
    }
}
