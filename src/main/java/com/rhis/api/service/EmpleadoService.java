package com.rhis.api.service;

import com.rhis.api.dto.EmpleadoReferenciaRequestDto;
import com.rhis.api.dto.EmpleadoRequestDto;
import com.rhis.api.dto.EmpleadoResponseDto;
import com.rhis.api.dto.ExperienciaLaboralRequestDto;
import com.rhis.api.exception.PuestoNotFoundException;
import com.rhis.api.exception.UserConflictException;
import com.rhis.api.mapper.EmpleadoMapper;
import com.rhis.api.model.*;
import com.rhis.api.repository.EmpleadoRepository;
import com.rhis.api.repository.PuestoRespository;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class EmpleadoService {

    private static final Random RANDOM = new Random();

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoService.class);

    private final EmpleadoRepository empleadoRepository;

    private final EmpleadoMapper empleadoMapper;

    private final PuestoRespository puestoRespository;

    private final KeycloakService keycloakService;

    private final String ROLE_RRHH = "rrhh";
    private final String ROLE_USER = "user";
    private final String ROLE_ADMINISTRATOR = "administracion";

    public EmpleadoService(EmpleadoRepository empleadoRepository, EmpleadoMapper empleadoMapper, PuestoRespository puestoRespository, KeycloakService keycloakService) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
        this.puestoRespository = puestoRespository;
        this.keycloakService = keycloakService;
    }

    /**
     * Funcion que crea un empleado
     *
     * @param empleadoRequestDto
     * @return el empleado reado
     * @throws PuestoNotFoundException
     */
    public EmpleadoResponseDto crearEmpleado(EmpleadoRequestDto empleadoRequestDto) throws PuestoNotFoundException, UserConflictException {
        var puesto = puestoRespository.findById(empleadoRequestDto.getPuesto())
                .orElseThrow(PuestoNotFoundException::new);

        var empleado = empleadoMapper.toEntity(empleadoRequestDto);

        empleado.setUsername(getUsername(empleado));
        String idEmpleado = keycloakService.createUser(empleado);
        empleado.setIdEmpleado(idEmpleado);
        empleado.setPuesto(puesto);
        empleado.setHabilitado(true);

        return empleadoMapper.toDto(empleadoRepository.save(empleado));
    }

    private String getUsername(Empleado empleado) {
        String primeraLetraNombre = empleado.getNombres().substring(0, 1).toLowerCase();
        String primerApellido = empleado.getApellidos().split(" ")[0].toLowerCase();

        int numerosAleatorios = 1000 + RANDOM.nextInt(9000);

        return primeraLetraNombre + primerApellido + numerosAleatorios;
    }

    /**
     * Funcion que obtiene todos los empleados de un puesto especifico || si no especificamos el puesto nos lista a todos los empleados
     *
     * @param idPuesto
     * @return empleados
     */
    public List<EmpleadoResponseDto> obtenerEmpleados(String idPuesto) {
        if (idPuesto != null) {
            return empleadoRepository.findAllByPuestoIdPuestoAndHabilitadoTrue(idPuesto)
                    .stream()
                    .map(empleadoMapper::toDto)
                    .toList();
        }

        return empleadoRepository.findAllByOrderByFechaIngresoAsc()
                .stream()
                .map(empleadoMapper::toDto)
                .toList();

    }

    public EmpleadoResponseDto getOneEmpleado(String idEmpleado) {
        var empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(EmptyStackException::new);

        return empleadoMapper.toDto(empleado);
    }

    /**
     * Funcion que modifica un empleado
     *
     * @param empleadoRequestDto
     * @return
     */
    public EmpleadoResponseDto editarEmpleado(EmpleadoRequestDto empleadoRequestDto) {
        var empleado = empleadoRepository.findById(empleadoRequestDto.getIdEmpleado()).orElseThrow();//Recueramos el empleado
        empleadoMapper.modificar(empleadoRequestDto, empleado);//seteamos los nuevos valores a ese empleado desde la clase mapper

        List<EmpleadoReferencia> nuevasReferencias = new ArrayList<>();//creamos una nueva lista de tipo EmpleadoReferencia

        // Recorrer la lista de referencias del request
        for (EmpleadoReferenciaRequestDto referenciaRequest : empleadoRequestDto.getEmpleadoReferencia()) {
            EmpleadoReferencia referenciaExistente = empleado.getEmpleadoReferencia().stream()
                    .filter(ref ->
                            ref.getIdEmpleadoReferencia().equalsIgnoreCase(referenciaRequest.getIdEmpleadoReferencia()))

                    .findFirst()
                    .orElse(null);

            if (referenciaExistente != null) {
                referenciaExistente.setNombres(referenciaRequest.getNombres());
                referenciaExistente.setApellidos(referenciaRequest.getApellidos());
                referenciaExistente.setTelefono(referenciaRequest.getTelefono());
                referenciaExistente.setDescripcion(referenciaRequest.getDescripcion());
                referenciaExistente.setEmpleado(empleado);
            } else {
                EmpleadoReferencia nuevaReferencia = new EmpleadoReferencia();
                nuevaReferencia.setNombres(referenciaRequest.getNombres());
                nuevaReferencia.setApellidos(referenciaRequest.getApellidos());
                nuevaReferencia.setTelefono(referenciaRequest.getTelefono());
                nuevaReferencia.setDescripcion(referenciaRequest.getDescripcion());
                nuevaReferencia.setEmpleado(empleado);

                nuevasReferencias.add(nuevaReferencia);
            }
        }

        empleado.getEmpleadoReferencia().addAll(nuevasReferencias);

        for (ExperienciaLaboralRequestDto laboralRequestDto : empleadoRequestDto.getExperienciaLaboral()) {
            EmpleadoExperienciaLaboral empleadoExperienciaLaboralExistente = empleado.getExperienciaLaboral().stream()
                    .filter(ref ->
                            ref.getIdExperienciaLaboral().equalsIgnoreCase(laboralRequestDto.getIdExperienciaLaboral()))

                    .findFirst()
                    .orElse(null);

            if (empleadoExperienciaLaboralExistente != null) {
                empleadoExperienciaLaboralExistente.setNombreEmpresa(laboralRequestDto.getNombreEmpresa());
                empleadoExperienciaLaboralExistente.setAntiguedad(laboralRequestDto.getAntiguedad());
                empleadoExperienciaLaboralExistente.setPuesto(laboralRequestDto.getPuesto());
                empleadoExperienciaLaboralExistente.setTelefono(laboralRequestDto.getTelefono());
                empleadoExperienciaLaboralExistente.setEmpleado(empleado);
            }
        }

        return empleadoMapper.toDto(empleadoRepository.save(empleado));
    }

    @PostConstruct
    public void init() {
        scheduler.schedule(() -> {
            try {
                createDefaultUsers();
            } catch (UserConflictException e) {
                logger.info("Tarea programada finalizada...");
            }
        }, 20, TimeUnit.SECONDS);
        scheduler.shutdown();
    }

    private void createDefaultUsers() throws UserConflictException {
        logger.info("Tarea programada empezada...");

        List<Empleado> empleados = new ArrayList<>();
        empleados.add(getDesarrolladorJunior());
        empleados.add(getJefeDepartamentoIt());

        empleados.add(getEncargadoNomina());
        empleados.add(getJefeRrhh());

        for (Empleado empleado : empleados) {
            String idEmpleado = keycloakService.createUser(empleado);
            empleado.setIdEmpleado(idEmpleado);

            empleadoRepository.save(empleado);
            logger.info("Empleado {} {} creado.", empleado.getNombres(), empleado.getApellidos());
        }

        logger.info("Tarea programada finalizada...");

    }

    private Empleado getDesarrolladorJunior() {
        Empleado empleado = new Empleado();

        empleado.setNombres("Brayan Javier");
        empleado.setApellidos("Marin de Leon");
        empleado.setDpi("2660339890114");
        empleado.setTelefono("32462029");
        empleado.setDireccion("5ta calle 5-16 zona 3 Palin, Escuintla");
        empleado.setGenero(null);  // No value provided for 'genero'
        empleado.setCorreo("javiermdleon95@gmail.com");
        empleado.setNit("12345");
        empleado.setNacionalidad("Guatemalteco");
        empleado.setNivelIngles("Intermedio");
        empleado.setFechaNacimiento(LocalDate.parse("1995-01-30"));
        empleado.setFechaIngreso(LocalDate.parse("2024-05-01"));
        empleado.setHabilitado(true);
        empleado.setEstadoCivil(null);  // No value provided for 'estado_civil'
        empleado.setSalario(BigDecimal.valueOf(6000));

        empleado.setRole(ROLE_USER);

        Puesto puesto = puestoRespository.findById("0eb88037-2b11-4ca0-a1b4-a9cd5ad713e5")
                .orElseThrow(null);
        empleado.setPuesto(puesto);
        empleado.setUsername(getUsername(empleado));

        EmpleadoReferencia referencia = new EmpleadoReferencia();
        referencia.setIdEmpleadoReferencia(UUID.randomUUID().toString());
        referencia.setNombres("Enma Anileidy");
        referencia.setApellidos("Marin de Leon");
        referencia.setTelefono("234356");
        referencia.setDescripcion("Hermana del colaborador");
        referencia.setEmpleado(empleado);

        EmpleadoExperienciaLaboral experienciaLaboral = new EmpleadoExperienciaLaboral();
        experienciaLaboral.setIdExperienciaLaboral(UUID.randomUUID().toString());
        experienciaLaboral.setNombreEmpresa("Shinwon GT");
        experienciaLaboral.setAntiguedad(10);
        experienciaLaboral.setTelefono("78488868");
        experienciaLaboral.setPuesto("Gerente de IT");
        experienciaLaboral.setDescripcion("Encargado de llevar todo el control del departamento de sistemas");
        experienciaLaboral.setEmpleado(empleado);

        EmpleadoNivelAcademico nivelAcademico = new EmpleadoNivelAcademico();

        nivelAcademico.setIdEmpleadoNivelAcademico(UUID.randomUUID().toString());
        nivelAcademico.setNivelAcademico("Graduado Ingeniero en Sistemas");
        nivelAcademico.setDescripcion("Estudio en la universidad mariano galvez");
        nivelAcademico.setEmpleado(empleado);

        return empleado;
    }

    private Empleado getJefeDepartamentoIt() {
        Empleado empleado = new Empleado();

        empleado.setNombres("Rody Keny");
        empleado.setApellidos("Marin Gonzalez");
        empleado.setDpi("2660339890114");
        empleado.setTelefono("32462029");
        empleado.setDireccion("5ta calle 5-16 zona 3 Palin, Escuintla");
        empleado.setGenero(null);
        empleado.setCorreo("keny@gmail.com");
        empleado.setNit("12345");
        empleado.setNacionalidad("Guatemalteco");
        empleado.setNivelIngles("Intermedio");
        empleado.setFechaNacimiento(LocalDate.parse("1988-01-30"));
        empleado.setFechaIngreso(LocalDate.parse("2024-05-01"));
        empleado.setHabilitado(true);
        empleado.setEstadoCivil(null);
        empleado.setSalario(BigDecimal.valueOf(6000.00));
        empleado.setUsername(getUsername(empleado));

        empleado.setRole(ROLE_USER);

        Puesto puesto = puestoRespository.findById("e1bb8734-3041-43e2-80b9-872e9cc5f731")
                .orElseThrow(null);
        empleado.setPuesto(puesto);

        EmpleadoReferencia referencia1 = new EmpleadoReferencia();
        referencia1.setIdEmpleadoReferencia(UUID.randomUUID().toString());
        referencia1.setNombres("Enma Anileidy");
        referencia1.setApellidos("Marin de Leon");
        referencia1.setTelefono("234356");
        referencia1.setDescripcion("Hermana del colaborador");
        referencia1.setEmpleado(empleado);

        EmpleadoExperienciaLaboral experienciaLaboral = new EmpleadoExperienciaLaboral();
        experienciaLaboral.setIdExperienciaLaboral(UUID.randomUUID().toString());
        experienciaLaboral.setNombreEmpresa("Shinwon GT");
        experienciaLaboral.setAntiguedad(10);
        experienciaLaboral.setTelefono("78488868");
        experienciaLaboral.setPuesto("Gerente de IT");
        experienciaLaboral.setDescripcion("Encargado de llevar todo el control del departamento de sistemas");
        experienciaLaboral.setEmpleado(empleado);

        EmpleadoNivelAcademico nivelAcademico2 = new EmpleadoNivelAcademico();
        nivelAcademico2.setIdEmpleadoNivelAcademico(UUID.randomUUID().toString());
        nivelAcademico2.setNivelAcademico("Graduado Ingeniero en Sistemas");
        nivelAcademico2.setDescripcion("Estudio en la universidad mariano galvez");
        nivelAcademico2.setEmpleado(empleado);

        return empleado;
    }

    private Empleado getEncargadoNomina() {
        Empleado empleado = new Empleado();
        empleado.setNombres("Allison Rocio");
        empleado.setApellidos("Alvarez Perez");
        empleado.setDpi("2660339890114");
        empleado.setTelefono("32462029");
        empleado.setDireccion("5ta calle 5-16 zona 3 Palin, Escuintla");
        empleado.setGenero(null);
        empleado.setCorreo("allison@gmail.com");
        empleado.setNit("12345");
        empleado.setNacionalidad("Guatemalteco");
        empleado.setNivelIngles("Avanzado");
        empleado.setFechaNacimiento(LocalDate.parse("1999-01-30"));
        empleado.setFechaIngreso(LocalDate.parse("2024-05-01"));
        empleado.setHabilitado(true);
        empleado.setEstadoCivil(null);
        empleado.setSalario(BigDecimal.valueOf(6000.00));
        Puesto puesto = puestoRespository.findById("c0b7e4b8-c4bc-463e-8847-3e4dc1fa3ba5")
                .orElseThrow(null);
        empleado.setPuesto(puesto);
        empleado.setUsername(getUsername(empleado));

        empleado.setRole(ROLE_RRHH);

        EmpleadoReferencia referencia3 = new EmpleadoReferencia();
        referencia3.setIdEmpleadoReferencia(UUID.randomUUID().toString());
        referencia3.setNombres("Enma Anileidy");
        referencia3.setApellidos("Marin de Leon");
        referencia3.setTelefono("234356");
        referencia3.setDescripcion("Hermana del colaborador");
        referencia3.setEmpleado(empleado);

        EmpleadoExperienciaLaboral experienciaLaboral3 = new EmpleadoExperienciaLaboral();
        experienciaLaboral3.setIdExperienciaLaboral(UUID.randomUUID().toString());
        experienciaLaboral3.setNombreEmpresa("Shinwon GT");
        experienciaLaboral3.setAntiguedad(10);
        experienciaLaboral3.setTelefono("78488868");
        experienciaLaboral3.setPuesto("Encargada de nominas");
        experienciaLaboral3.setDescripcion("Responsable de llevar el control de nomina del personal");
        experienciaLaboral3.setEmpleado(empleado);

        EmpleadoNivelAcademico nivelAcademico3 = new EmpleadoNivelAcademico();
        nivelAcademico3.setIdEmpleadoNivelAcademico(UUID.randomUUID().toString());
        nivelAcademico3.setNivelAcademico("Graduada de Psicologia Industrial");
        nivelAcademico3.setDescripcion("Estudio en la universidad mariano galvez");
        nivelAcademico3.setEmpleado(empleado);

        return empleado;
    }

    private Empleado getJefeRrhh() {
        Empleado empleado = new Empleado();
        empleado.setNombres("Leslie Julissa");
        empleado.setApellidos("Ruiz");
        empleado.setDpi("111111111");
        empleado.setTelefono("34567890");
        empleado.setDireccion("Colinas de Monte Maria");
        empleado.setGenero(null);
        empleado.setCorreo("leslie@gmail.com");
        empleado.setNit("666666");
        empleado.setNacionalidad("Guatemalteca");
        empleado.setNivelIngles("Avanzado");
        empleado.setFechaNacimiento(LocalDate.parse("1996-09-18"));
        empleado.setFechaIngreso(LocalDate.parse("2024-05-01"));
        empleado.setHabilitado(true);
        empleado.setEstadoCivil(null);
        empleado.setSalario(BigDecimal.valueOf(6000.00));
        Puesto puesto = puestoRespository.findById("e1bb8734-3041-43e2-80b9-983e1cc6f731")
                .orElseThrow(null);
        empleado.setPuesto(puesto);
        empleado.setUsername(getUsername(empleado));

        empleado.setRole(ROLE_RRHH);

        EmpleadoReferencia referencia4 = new EmpleadoReferencia();
        referencia4.setIdEmpleadoReferencia(UUID.randomUUID().toString());
        referencia4.setNombres("Enma Anileidy");
        referencia4.setApellidos("Marin de Leon");
        referencia4.setTelefono("234356");
        referencia4.setDescripcion("Hermana del colaborador");
        referencia4.setEmpleado(empleado);

        empleado.setEmpleadoReferencia(List.of(referencia4));

        EmpleadoExperienciaLaboral experienciaLaboral4 = new EmpleadoExperienciaLaboral();
        experienciaLaboral4.setIdExperienciaLaboral(UUID.randomUUID().toString());
        experienciaLaboral4.setNombreEmpresa("BAM");
        experienciaLaboral4.setAntiguedad(10);
        experienciaLaboral4.setTelefono("78488868");
        experienciaLaboral4.setPuesto("Jefe de RRHH");
        experienciaLaboral4.setDescripcion("Responsable de llevar el control del personal");
        experienciaLaboral4.setEmpleado(empleado);

        empleado.setExperienciaLaboral(List.of(experienciaLaboral4));

        EmpleadoNivelAcademico nivelAcademico4 = new EmpleadoNivelAcademico();
        nivelAcademico4.setIdEmpleadoNivelAcademico(UUID.randomUUID().toString());
        nivelAcademico4.setNivelAcademico("Graduada de Psicologia Industrial");
        nivelAcademico4.setDescripcion("Estudio en la universidad mariano galvez");
        nivelAcademico4.setEmpleado(empleado);

        empleado.setNivelAcademico(List.of(nivelAcademico4));

        return empleado;
    }

}
