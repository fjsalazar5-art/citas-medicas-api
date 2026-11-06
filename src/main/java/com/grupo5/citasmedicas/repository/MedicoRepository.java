package com.grupo5.citasmedicas.repository;

import com.grupo5.citasmedicas.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    List<Medico> findByEspecialidadNombre(String nombre);

    boolean existsByCorreo(String correo);

}
