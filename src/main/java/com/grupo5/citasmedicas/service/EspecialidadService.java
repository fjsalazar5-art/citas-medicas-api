package com.grupo5.citasmedicas.service;

import com.grupo5.citasmedicas.entity.Especialidad;
import com.grupo5.citasmedicas.exception.DuplicateResourceException;
import com.grupo5.citasmedicas.exception.ResourceNotFoundException;
import com.grupo5.citasmedicas.repository.EspecialidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadService {

    private final EspecialidadRepository especialidadRepository;

    public EspecialidadService(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    public List<Especialidad> listar() {
        return especialidadRepository.findAll();
    }

    public Especialidad obtenerPorId(Long id) {
        return especialidadRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Especialidad no encontrada"));
    }

    public Especialidad guardar(Especialidad especialidad) {

        if (especialidadRepository.existsByNombre(
                especialidad.getNombre())) {

            throw new DuplicateResourceException(
                    "La especialidad ya existe");
        }

        return especialidadRepository.save(especialidad);
    }

    public Especialidad actualizar(Long id,
                                   Especialidad datos) {

        Especialidad especialidad = obtenerPorId(id);

        especialidad.setNombre(datos.getNombre());
        especialidad.setDescripcion(datos.getDescripcion());

        return especialidadRepository.save(especialidad);
    }

    public void eliminar(Long id) {

        obtenerPorId(id);

        especialidadRepository.deleteById(id);
    }
}
