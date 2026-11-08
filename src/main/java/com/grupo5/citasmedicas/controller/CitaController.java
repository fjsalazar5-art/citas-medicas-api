package com.grupo5.citasmedicas.controller;

import com.grupo5.citasmedicas.dto.CitaDTO;
import com.grupo5.citasmedicas.entity.Cita;
import com.grupo5.citasmedicas.mapper.CitaMapper;
import com.grupo5.citasmedicas.service.CitaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * Endpoints REST para la gestión de citas médicas.
 * Las reglas de negocio (no fechas pasadas, no dos citas del mismo
 * médico en el mismo horario, no cancelar dos veces) viven en CitaService.
 */
@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;
    private final CitaMapper citaMapper;

    public CitaController(CitaService citaService, CitaMapper citaMapper) {
        this.citaService = citaService;
        this.citaMapper = citaMapper;
    }

    @GetMapping
    public ResponseEntity<List<CitaDTO>> listar() {
        return ResponseEntity.ok(citaMapper.toDTOList(citaService.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(citaMapper.toDTO(citaService.obtenerPorId(id)));
    }

    /**
     * Consulta personalizada (Spring Data): citas de una fecha.
     * Ejemplo: GET /api/citas/fecha/2026-11-20
     */
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<CitaDTO>> buscarPorFecha(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fecha) {
        return ResponseEntity.ok(
                citaMapper.toDTOList(citaService.buscarPorFecha(fecha)));
    }

    /**
     * Consulta personalizada (Spring Data): citas por estado.
     * Ejemplo: GET /api/citas/estado/PROGRAMADA
     */
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<CitaDTO>> buscarPorEstado(
            @PathVariable String estado) {
        return ResponseEntity.ok(
                citaMapper.toDTOList(citaService.buscarPorEstado(estado)));
    }

    /**
     * Consulta de agenda médica: citas de un médico ordenadas por fecha y hora.
     * Ejemplo: GET /api/citas/medico/1
     */
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<CitaDTO>> agendaPorMedico(
            @PathVariable Long medicoId) {
        return ResponseEntity.ok(
                citaMapper.toDTOList(citaService.agendaPorMedico(medicoId)));
    }

    @PostMapping
    public ResponseEntity<CitaDTO> crear(@RequestBody CitaDTO dto) {
        Cita creada = citaService.guardar(citaMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(citaMapper.toDTO(creada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> actualizar(@PathVariable Long id,
                                              @RequestBody CitaDTO dto) {
        Cita actualizada = citaService.actualizar(id, citaMapper.toEntity(dto));
        return ResponseEntity.ok(citaMapper.toDTO(actualizada));
    }

    /**
     * Cancela una cita (cambia su estado a CANCELADA sin borrarla).
     * Ejemplo: PATCH /api/citas/3/cancelar
     */
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<CitaDTO> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(citaMapper.toDTO(citaService.cancelar(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        citaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
