package com.rhis.api.service;

import com.rhis.api.dto.ImpuestoRequestDto;
import com.rhis.api.dto.ImpuestosResponseDto;
import com.rhis.api.mapper.ImpuestosMapper;
import com.rhis.api.repository.ImpuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpuestosService {

    private final ImpuestoRepository impuestoRepository;
    private final ImpuestosMapper impuestosMapper;


    public ImpuestosService(ImpuestoRepository impuestoRepository, ImpuestosMapper impuestosMapper) {
        this.impuestoRepository = impuestoRepository;
        this.impuestosMapper = impuestosMapper;
    }

    /**
     * Funcion que registra nuevos impuestos
     *
     * @param impuestoRequestDto
     * @return
     */
    public ImpuestosResponseDto crearImpuesto(ImpuestoRequestDto impuestoRequestDto){
        System.out.println(impuestoRequestDto);
        var impuesto = impuestosMapper.toEntity(impuestoRequestDto);
        return impuestosMapper.toDto(impuestoRepository.save(impuesto));
    }


    /**
     * Metodo que retorna la lista de impuestos
     * @return
     */
    public List<ImpuestosResponseDto> listarImpuestos(){
        return impuestoRepository.findAll()
                .stream()
                .map(impuestosMapper::toDto)
                .toList();
    }

    /**
     * Metodo que modifica un impuesto
     *
     * @param impuestoRequestDto
     * @return
     */
    public ImpuestosResponseDto modificarImpuesto(ImpuestoRequestDto impuestoRequestDto){
        var impuesto = impuestoRepository.findById(impuestoRequestDto.getIdImpuesto()).orElseThrow();
        impuesto.setNombre(impuestoRequestDto.getNombre());
        impuesto.setPorcentaje(impuestoRequestDto.getPorcentaje());
        impuesto.setDescripcion(impuestoRequestDto.getDescripcion());
        return impuestosMapper.toDto(impuestoRepository.save(impuesto));
    }

    public void eliminarImpuesto(String idImpuesto){
        var impuesto = impuestoRepository.findById(idImpuesto);
        if (impuesto != null){
            impuestoRepository.deleteById(idImpuesto);
        }
    }
}
