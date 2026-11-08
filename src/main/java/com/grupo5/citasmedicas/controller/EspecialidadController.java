package com.grupo5.citasmedicas.controller;

import com.grupo5.citasmedicas.dto.EspecialidadDTO;
import com.grupo5.citasmedicas.entity.Especialidad;
import com.grupo5.citasmedicas.mapper.EspecialidadMapper;
import com.grupo5.citasmedicas.service.EspecialidadService;
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
 * Endpoints REST para la gestión de especialidades médicas.
 * El controller solo recibe la petición, delega al service
 * y devuelve la respuesta HTTP; no contiene lógica de negocio.
 */
@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    private final EspecialidadService especialidadService;
    private final EspecialidadMapper especialidadMapper;

    public EspecialidadController(EspecialidadService especialidadService,
                                  EspecialidadMapper especialidadMapper) {
        this.especialidadService = especialidadService;
        this.especialidadMapper = especialidadMapper;
    }

    @GetMapping
    public ResponseEntity<List<EspecialidadDTO>> listar() {
        return ResponseEntity.ok(
                especialidadMapper.toDTOList(especialidadService.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(
                especialidadMapper.toDTO(especialidadService.obtenerPorId(id)));
    }

    @PostMapping
    public ResponseEntity<EspecialidadDTO> crear(@RequestBody EspecialidadDTO dto) {
        Especialidad creada = especialidadService.guardar(
                especialidadMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(especialidadMapper.toDTO(creada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadDTO> actualizar(@PathVariable Long id,
                                                      @RequestBody EspecialidadDTO dto) {
        Especialidad actualizada = especialidadService.actualizar(
                id, especialidadMapper.toEntity(dto));
        return ResponseEntity.ok(especialidadMapper.toDTO(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        especialidadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
