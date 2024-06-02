package com.rhis.api.service;

import com.rhis.api.dto.PagoPlanillaRequestDto;
import com.rhis.api.dto.PagoPlanillaResponseDto;
import com.rhis.api.mapper.PagoPlanillaMapper;
import com.rhis.api.model.Empleado;
import com.rhis.api.model.Impuesto;
import com.rhis.api.model.PagoPlanilla;
import com.rhis.api.repository.EmpleadoRepository;
import com.rhis.api.repository.ImpuestoRepository;
import com.rhis.api.repository.PagoPlanillaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PagoPlanillaService {

    private final PagoPlanillaRepository pagoPlanillaRepository;
    private final PagoPlanillaMapper pagoPlanillaMapper;
    private final EmpleadoRepository empleadoRepository;
    private final ImpuestoRepository impuestoRepository;

    public PagoPlanillaService(PagoPlanillaRepository pagoPlanillaRepository, PagoPlanillaMapper pagoPlanillaMapper, EmpleadoRepository empleadoRepository, ImpuestoRepository impuestoRepository) {
        this.pagoPlanillaRepository = pagoPlanillaRepository;
        this.pagoPlanillaMapper = pagoPlanillaMapper;
        this.empleadoRepository = empleadoRepository;
        this.impuestoRepository = impuestoRepository;
    }

    /**
     * Funcion que genera una planilla de un rango de fechas de todos los empleados
     *
     * @param pagoPlanillaRequestDto
     * @return
     */
    public PagoPlanillaResponseDto generarPlanilla(PagoPlanillaRequestDto pagoPlanillaRequestDto) {
        var empleados = empleadoRepository.findAll();
        var impuestos = impuestoRepository.findAll();

        for (Empleado empleado : empleados) {
            PagoPlanilla pagoPlanilla = new PagoPlanilla();
            pagoPlanilla.setEmpleado(empleado);
            pagoPlanilla.setBonificacionLey(BigDecimal.valueOf(250.00));
            pagoPlanilla.setSueldoBase(empleado.getSalario());
            pagoPlanilla.setFechaInicio(pagoPlanillaRequestDto.getFechaInicio());
            pagoPlanilla.setFechaFinal(pagoPlanillaRequestDto.getFechaFinal());

            for (Impuesto impuesto : impuestos) {
                var sal = pagoPlanilla.getSueldoBase().add(pagoPlanilla.getBonificacionLey());
                var impuest = sal.multiply(impuesto.getPorcentaje());
                var salarioNeto = sal.subtract(impuest);
                pagoPlanilla.setSalarioNeto(salarioNeto);
            }
            pagoPlanillaRepository.save(pagoPlanilla);
        }
        return null;

    }

    /**
     * Funcion que lista las planillas de todos los usuarios
     * @return
     */
    public List<PagoPlanillaResponseDto> obtenerPlanillas() {
        return pagoPlanillaRepository.findAll()
                .stream()
                .map(pagoPlanillaMapper::toDto)
                .toList();
    }
}
