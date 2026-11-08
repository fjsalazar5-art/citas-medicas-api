package com.grupo5.citasmedicas.mapper;

import com.grupo5.citasmedicas.dto.MedicoDTO;
import com.grupo5.citasmedicas.entity.Medico;
import com.grupo5.citasmedicas.service.EspecialidadService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Convierte entre la entidad Medico y su DTO.
 * Resuelve especialidadId a través de EspecialidadService,
 * que lanza ResourceNotFoundException (404) si no existe.
 */
@Component
public class MedicoMapper {

    private final EspecialidadService especialidadService;

    public MedicoMapper(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    public MedicoDTO toDTO(Medico medico) {
        return new MedicoDTO(
                medico.getId(),
                medico.getNombres(),
                medico.getApellidos(),
                medico.getCorreo(),
                medico.getTelefono(),
                medico.getEspecialidad() != null
                        ? medico.getEspecialidad().getId()
                        : null
        );
    }

    public List<MedicoDTO> toDTOList(List<Medico> medicos) {
        return medicos.stream().map(this::toDTO).toList();
    }

    public Medico toEntity(MedicoDTO dto) {
        Medico medico = new Medico();
        medico.setNombres(dto.getNombres());
        medico.setApellidos(dto.getApellidos());
        medico.setCorreo(dto.getCorreo());
        medico.setTelefono(dto.getTelefono());
        medico.setEspecialidad(
                especialidadService.obtenerPorId(dto.getEspecialidadId()));
        return medico;
    }
}
