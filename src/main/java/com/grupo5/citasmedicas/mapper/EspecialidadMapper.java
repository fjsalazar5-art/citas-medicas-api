package com.grupo5.citasmedicas.mapper;

import com.grupo5.citasmedicas.dto.EspecialidadDTO;
import com.grupo5.citasmedicas.entity.Especialidad;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Convierte entre la entidad Especialidad y su DTO.
 */
@Component
public class EspecialidadMapper {

    public EspecialidadDTO toDTO(Especialidad especialidad) {
        return new EspecialidadDTO(
                especialidad.getId(),
                especialidad.getNombre(),
                especialidad.getDescripcion()
        );
    }

    public List<EspecialidadDTO> toDTOList(List<Especialidad> especialidades) {
        return especialidades.stream().map(this::toDTO).toList();
    }

    public Especialidad toEntity(EspecialidadDTO dto) {
        Especialidad especialidad = new Especialidad();
        especialidad.setNombre(dto.getNombre());
        especialidad.setDescripcion(dto.getDescripcion());
        return especialidad;
    }
}
