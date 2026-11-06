package com.grupo5.citasmedicas.service;

import com.grupo5.citasmedicas.entity.Medico;
import com.grupo5.citasmedicas.exception.DuplicateResourceException;
import com.grupo5.citasmedicas.exception.ResourceNotFoundException;
import com.grupo5.citasmedicas.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<Medico> listar() {
        return medicoRepository.findAll();
    }

    public Medico obtenerPorId(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Médico no encontrado"));
    }

    public Medico guardar(Medico medico) {

        if (medicoRepository.existsByCorreo(
                medico.getCorreo())) {

            throw new DuplicateResourceException(
                    "Ya existe un médico con ese correo");
        }

        return medicoRepository.save(medico);
    }

    public Medico actualizar(Long id, Medico datos) {

        Medico medico = obtenerPorId(id);

        medico.setNombres(datos.getNombres());
        medico.setApellidos(datos.getApellidos());
        medico.setCorreo(datos.getCorreo());
        medico.setTelefono(datos.getTelefono());
        medico.setEspecialidad(datos.getEspecialidad());

        return medicoRepository.save(medico);
    }

    public List<Medico> buscarPorEspecialidad(
            String especialidad) {

        return medicoRepository
                .findByEspecialidadNombre(especialidad);
    }

    public void eliminar(Long id) {

        obtenerPorId(id);

        medicoRepository.deleteById(id);
    }
}