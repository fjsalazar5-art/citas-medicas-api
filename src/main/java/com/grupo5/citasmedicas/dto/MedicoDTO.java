package com.grupo5.citasmedicas.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDTO {

    private Long id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
    private Long especialidadId;

}