package com.grupo5.citasmedicas.service;
import com.grupo5.citasmedicas.entity.Cita;
import com.grupo5.citasmedicas.exception.AppointmentConflictException;
import com.grupo5.citasmedicas.exception.ResourceNotFoundException;
import com.grupo5.citasmedicas.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CitaService {

    private final CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public List<Cita> listar() {
        return citaRepository.findAll();
    }

    public Cita obtenerPorId(Long id) {
        return citaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cita no encontrada"));
    }

    public Cita guardar(Cita cita) {

        if (cita.getFecha().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "No se pueden registrar citas en fechas pasadas");
        }

        if (citaRepository.existsByMedicoIdAndFechaAndHora(
                cita.getMedico().getId(),
                cita.getFecha(),
                cita.getHora())) {

            throw new AppointmentConflictException(
                    "El médico ya tiene una cita en ese horario");
        }

        return citaRepository.save(cita);
    }

    public Cita actualizar(Long id, Cita datos) {

        Cita cita = obtenerPorId(id);

        cita.setFecha(datos.getFecha());
        cita.setHora(datos.getHora());
        cita.setMotivo(datos.getMotivo());
        cita.setEstado(datos.getEstado());
        cita.setMedico(datos.getMedico());
        cita.setPaciente(datos.getPaciente());

        return citaRepository.save(cita);
    }

    public List<Cita> buscarPorFecha(LocalDate fecha) {
        return citaRepository.findByFecha(fecha);
    }

    public List<Cita> buscarPorEstado(String estado) {
        return citaRepository.findByEstado(estado);
    }

    public void eliminar(Long id) {

        obtenerPorId(id);

        citaRepository.deleteById(id);
    }
}
