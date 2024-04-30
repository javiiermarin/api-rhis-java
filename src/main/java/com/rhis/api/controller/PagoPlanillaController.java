package com.rhis.api.controller;

import com.rhis.api.dto.PagoPlanillaRequestDto;
import com.rhis.api.dto.PagoPlanillaResponseDto;
import com.rhis.api.service.PagoPlanillaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rhis/pago_planillas")
public class PagoPlanillaController {

    private final PagoPlanillaService pagoPlanillaService;

    public PagoPlanillaController(PagoPlanillaService pagoPlanillaService) {
        this.pagoPlanillaService = pagoPlanillaService;
    }

    @PostMapping
    public ResponseEntity<PagoPlanillaResponseDto> generarPlanilla(
            @RequestBody @Valid PagoPlanillaRequestDto pagoPlanillaRequestDto){
        var pagoPlanilla = pagoPlanillaService.generarPlanilla(pagoPlanillaRequestDto);

        return new ResponseEntity<>(pagoPlanilla, HttpStatus.CREATED);
    }
}
