package com.grupo5.citasmedicas.repository;

import com.grupo5.citasmedicas.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    boolean existsByCedula(String cedula);

}
