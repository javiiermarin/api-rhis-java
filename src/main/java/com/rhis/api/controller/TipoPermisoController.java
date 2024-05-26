package com.rhis.api.controller;

import com.rhis.api.dto.TipoPermisoRequestDto;
import com.rhis.api.dto.TipoPermisoResposeDto;
import com.rhis.api.service.TipoPermisoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rhis/tipoPermisos")
public class TipoPermisoController {

    private final TipoPermisoService tipoPermisoService;

    public TipoPermisoController(TipoPermisoService tipoPermisoService) {
        this.tipoPermisoService = tipoPermisoService;
    }

    @PostMapping
    public ResponseEntity<TipoPermisoResposeDto> crearTipoPermiso(@RequestBody @Valid TipoPermisoRequestDto tipoPermisoRequestDto){
        var tipoPermiso = tipoPermisoService.crearTipoPermiso(tipoPermisoRequestDto);

        return new ResponseEntity<>(tipoPermiso, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TipoPermisoResposeDto>> obtenerTipoPermisos(){
        var tipoPermisos = tipoPermisoService.obtenerTipoPermiso();
        return new ResponseEntity<>(tipoPermisos, HttpStatus.OK);
    }
}
