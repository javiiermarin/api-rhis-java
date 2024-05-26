package com.rhis.api.controller;

import com.rhis.api.dto.ImpuestoRequestDto;
import com.rhis.api.dto.ImpuestosResponseDto;
import com.rhis.api.service.ImpuestosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rhis/impuestos")
public class ImpuestoController {

    private final ImpuestosService impuestosService;

    public ImpuestoController(ImpuestosService impuestosService) {
        this.impuestosService = impuestosService;
    }

    /**
     * peticion que registra un nuevo impuesto
     *
     * @param impuestoRequestDto
     * @return
     */
    @PostMapping
    public ResponseEntity<ImpuestosResponseDto> registrarImpuesto(@RequestBody @Valid ImpuestoRequestDto impuestoRequestDto) {
        var impuesto = impuestosService.crearImpuesto(impuestoRequestDto);
        return new ResponseEntity<>(impuesto, HttpStatus.CREATED);
    }

    /**
     * peticion que lista todos los impuestos
     */
    @GetMapping
    public ResponseEntity<List<ImpuestosResponseDto>> listarImpuestos(){
        var impuesto = impuestosService.listarImpuestos();
        return new ResponseEntity<>(impuesto, HttpStatus.OK);
    }

    /**
     * Peticion que modifica un puesto
     *
     * @param impuestoRequestDto
     * @return
     */
    @PutMapping
    public ResponseEntity<ImpuestosResponseDto> modificarImpuesto(
            @RequestBody @Valid ImpuestoRequestDto impuestoRequestDto){
        var impuesto = impuestosService.modificarImpuesto(impuestoRequestDto);

        return new ResponseEntity<>(impuesto, HttpStatus.OK);
    }



    /**
     * Peticion que elimina un impuesto a partir de su ID
     * @param idImpuesto
     * @return
     */
    @DeleteMapping("/{idImpuesto}")
    public ResponseEntity<Void> elimiarImpuesto(@PathVariable String idImpuesto){
        impuestosService.eliminarImpuesto(idImpuesto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
