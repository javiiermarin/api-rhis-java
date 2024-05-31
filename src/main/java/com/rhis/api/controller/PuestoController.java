package com.rhis.api.controller;

import com.rhis.api.dto.PuestoRequestDto;
import com.rhis.api.dto.PuestoResponseDto;
import com.rhis.api.exception.DivisionNotFoundException;
import com.rhis.api.exception.PuestoNotFoundException;
import com.rhis.api.service.PuestoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rhis/puestos")
public class PuestoController {

    private final PuestoService puestoService;

    public PuestoController(PuestoService puestoService) {
        this.puestoService = puestoService;
    }

    /**
     * peticion para crear un puesto
     *
     * @param puestoRequestDto
     * @return
     */
    @PostMapping
    public ResponseEntity<PuestoResponseDto> crearPuesto(@RequestBody @Valid PuestoRequestDto puestoRequestDto) throws DivisionNotFoundException {
        var puesto = puestoService.crearPuesto(puestoRequestDto);
        return new ResponseEntity<>(puesto, HttpStatus.OK);
    }

    /**
     * peticion para listar los puestos
     *
     * @param idDivision
     * @return
     * @throws PuestoNotFoundException
     */
    @GetMapping
    public ResponseEntity<List<PuestoResponseDto>> obtenerPuestosPorDivision(
            @RequestParam(value = "idDivision", required = false) String idDivision
    ) {
        var puestoDto = puestoService.obtenerPuestosPorDivision(idDivision);
        return new ResponseEntity<>(puestoDto, HttpStatus.OK);
    }

    /**
     * peticion para modificar un puesto
     *
     * @param puestoRequestDto
     * @return
     * @throws PuestoNotFoundException
     */
    @PutMapping
    public ResponseEntity<PuestoResponseDto> modificarPuesto(
            @RequestBody @Valid PuestoRequestDto puestoRequestDto
    ) throws PuestoNotFoundException, DivisionNotFoundException {
        var puesto = puestoService.modificarPuesto(puestoRequestDto);

        return new ResponseEntity<>(puesto, HttpStatus.OK);
    }

    @DeleteMapping("/{idPuesto}")
    public ResponseEntity<Void> eliminarPuesto(@PathVariable String idPuesto) {
        puestoService.eliminarPuesto(idPuesto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
