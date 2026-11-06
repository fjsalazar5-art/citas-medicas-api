package com.grupo5.citasmedicas.service;
import com.grupo5.citasmedicas.entity.Paciente;
import com.grupo5.citasmedicas.exception.DuplicateResourceException;
import com.grupo5.citasmedicas.exception.ResourceNotFoundException;
import com.grupo5.citasmedicas.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }

    public Paciente obtenerPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Paciente no encontrado"));
    }

    public Paciente guardar(Paciente paciente) {

        if (pacienteRepository.existsByCedula(
                paciente.getCedula())) {

            throw new DuplicateResourceException(
                    "Ya existe un paciente con esa cédula");
        }

        if (paciente.getFechaNacimiento()
                .isAfter(LocalDate.now())) {

            throw new IllegalArgumentException(
                    "Fecha de nacimiento inválida");
        }

        return pacienteRepository.save(paciente);
    }

    public Paciente actualizar(Long id,
                               Paciente datos) {

        Paciente paciente = obtenerPorId(id);

        paciente.setNombres(datos.getNombres());
        paciente.setApellidos(datos.getApellidos());
        paciente.setCedula(datos.getCedula());
        paciente.setCorreo(datos.getCorreo());
        paciente.setTelefono(datos.getTelefono());
        paciente.setFechaNacimiento(
                datos.getFechaNacimiento());

        return pacienteRepository.save(paciente);
    }

    public void eliminar(Long id) {

        obtenerPorId(id);

        pacienteRepository.deleteById(id);
    }
}