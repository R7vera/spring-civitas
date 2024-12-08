package com.civitas.pasajes.buses.dto;

import com.civitas.pasajes.buses.entity.Persona;
import com.civitas.pasajes.buses.entity.Usuario.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {
    private int id;
    private Persona persona; // Relación simplificada con Persona (solo el ID)
    private String correo;
    private String usuario;
    private String contrasenia;
    private TipoUsuario tipoUsuario; // Enum directamente
}
