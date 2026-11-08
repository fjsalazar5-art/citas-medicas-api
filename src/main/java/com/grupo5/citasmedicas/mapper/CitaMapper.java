package com.grupo5.citasmedicas.mapper;

import com.grupo5.citasmedicas.dto.CitaDTO;
import com.grupo5.citasmedicas.entity.Cita;
import com.grupo5.citasmedicas.service.MedicoService;
import com.grupo5.citasmedicas.service.PacienteService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Convierte entre la entidad Cita y su DTO.
 * Resuelve medicoId y pacienteId a través de los services,
 * que lanzan ResourceNotFoundException (404) si no existen.
 */
@Component
public class CitaMapper {

    private final MedicoService medicoService;
    private final PacienteService pacienteService;

    public CitaMapper(MedicoService medicoService,
                      PacienteService pacienteService) {
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
    }

    public CitaDTO toDTO(Cita cita) {
        return new CitaDTO(
                cita.getId(),
                cita.getFecha(),
                cita.getHora(),
                cita.getMotivo(),
                cita.getEstado(),
                cita.getMedico() != null ? cita.getMedico().getId() : null,
                cita.getPaciente() != null ? cita.getPaciente().getId() : null
        );
    }

    public List<CitaDTO> toDTOList(List<Cita> citas) {
        return citas.stream().map(this::toDTO).toList();
    }

    public Cita toEntity(CitaDTO dto) {
        Cita cita = new Cita();
        cita.setFecha(dto.getFecha());
        cita.setHora(dto.getHora());
        cita.setMotivo(dto.getMotivo());
        cita.setEstado(dto.getEstado());
        cita.setMedico(medicoService.obtenerPorId(dto.getMedicoId()));
        cita.setPaciente(pacienteService.obtenerPorId(dto.getPacienteId()));
        return cita;
    }
}
