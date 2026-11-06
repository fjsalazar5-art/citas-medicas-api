package com.grupo5.citasmedicas.repository;

import com.grupo5.citasmedicas.entity.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

    boolean existsByNombre(String nombre);

}