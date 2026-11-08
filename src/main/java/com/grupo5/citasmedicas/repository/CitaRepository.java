package com.grupo5.citasmedicas.repository;

import com.grupo5.citasmedicas.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByFecha(LocalDate fecha);

    List<Cita> findByEstado(String estado);

    List<Cita> findByMedicoIdOrderByFechaAscHoraAsc(Long medicoId);

    boolean existsByMedicoIdAndFechaAndHora(
            Long medicoId,
            LocalDate fecha,
            LocalTime hora
    );

}