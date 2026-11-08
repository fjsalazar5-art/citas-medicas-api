package com.grupo5.citasmedicas.controller;

import com.grupo5.citasmedicas.dto.PacienteDTO;
import com.grupo5.citasmedicas.entity.Paciente;
import com.grupo5.citasmedicas.mapper.PacienteMapper;
import com.grupo5.citasmedicas.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Endpoints REST para la gestión de pacientes.
 */
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;
    private final PacienteMapper pacienteMapper;

    public PacienteController(PacienteService pacienteService,
                              PacienteMapper pacienteMapper) {
        this.pacienteService = pacienteService;
        this.pacienteMapper = pacienteMapper;
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listar() {
        return ResponseEntity.ok(
                pacienteMapper.toDTOList(pacienteService.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                pacienteMapper.toDTO(pacienteService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> crear(@RequestBody PacienteDTO dto) {
        Paciente creado = pacienteService.guardar(pacienteMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pacienteMapper.toDTO(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> actualizar(@PathVariable Long id,
                                                  @RequestBody PacienteDTO dto) {
        Paciente actualizado = pacienteService.actualizar(
                id, pacienteMapper.toEntity(dto));
        return ResponseEntity.ok(pacienteMapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pacienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
