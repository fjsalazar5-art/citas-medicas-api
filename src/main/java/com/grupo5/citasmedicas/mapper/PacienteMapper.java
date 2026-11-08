package com.grupo5.citasmedicas.mapper;

import com.grupo5.citasmedicas.dto.PacienteDTO;
import com.grupo5.citasmedicas.entity.Paciente;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Convierte entre la entidad Paciente y su DTO.
 */
@Component
public class PacienteMapper {

    public PacienteDTO toDTO(Paciente paciente) {
        return new PacienteDTO(
                paciente.getId(),
                paciente.getNombres(),
                paciente.getApellidos(),
                paciente.getCedula(),
                paciente.getCorreo(),
                paciente.getTelefono(),
                paciente.getFechaNacimiento()
        );
    }

    public List<PacienteDTO> toDTOList(List<Paciente> pacientes) {
        return pacientes.stream().map(this::toDTO).toList();
    }

    public Paciente toEntity(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setNombres(dto.getNombres());
        paciente.setApellidos(dto.getApellidos());
        paciente.setCedula(dto.getCedula());
        paciente.setCorreo(dto.getCorreo());
        paciente.setTelefono(dto.getTelefono());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        return paciente;
    }
}
