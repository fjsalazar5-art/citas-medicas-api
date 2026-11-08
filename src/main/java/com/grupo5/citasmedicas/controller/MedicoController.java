package com.grupo5.citasmedicas.controller;

import com.grupo5.citasmedicas.dto.MedicoDTO;
import com.grupo5.citasmedicas.entity.Medico;
import com.grupo5.citasmedicas.mapper.MedicoMapper;
import com.grupo5.citasmedicas.service.MedicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Endpoints REST para la gestión de médicos.
 */
@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private final MedicoService medicoService;
    private final MedicoMapper medicoMapper;

    public MedicoController(MedicoService medicoService,
                            MedicoMapper medicoMapper) {
        this.medicoService = medicoService;
        this.medicoMapper = medicoMapper;
    }

    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listar() {
        return ResponseEntity.ok(
                medicoMapper.toDTOList(medicoService.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                medicoMapper.toDTO(medicoService.obtenerPorId(id)));
    }

    /**
     * Consulta personalizada (Spring Data): médicos por nombre de especialidad.
     * Ejemplo: GET /api/medicos/buscar?especialidad=Cardiología
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<MedicoDTO>> buscarPorEspecialidad(
            @RequestParam String especialidad) {
        return ResponseEntity.ok(medicoMapper.toDTOList(
                medicoService.buscarPorEspecialidad(especialidad)));
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> crear(@RequestBody MedicoDTO dto) {
        Medico creado = medicoService.guardar(medicoMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(medicoMapper.toDTO(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> actualizar(@PathVariable Long id,
                                                @RequestBody MedicoDTO dto) {
        Medico actualizado = medicoService.actualizar(
                id, medicoMapper.toEntity(dto));
        return ResponseEntity.ok(medicoMapper.toDTO(actualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        medicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
