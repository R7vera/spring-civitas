package com.civitas.pasajes.buses.validator;

import com.civitas.pasajes.buses.entity.Usuario;
import com.civitas.pasajes.buses.exception.ValidateException;

public class UsuarioValidator {

    public static void save(Usuario usuario) {
        // Validar persona
        if (usuario.getPersona() == null) {
            throw new ValidateException("La persona asociada al usuario es requerida");
        }

        // Validar correo
        if (usuario.getCorreo() == null || usuario.getCorreo().isEmpty()) {
            throw new ValidateException("El correo electrónico es requerido");
        }
        if (!usuario.getCorreo().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            throw new ValidateException("El correo electrónico tiene un formato inválido");
        }

        // Validar nombre de usuario
        if (usuario.getUsuario() == null || usuario.getUsuario().isEmpty()) {
            throw new ValidateException("El nombre de usuario es requerido");
        }
        if (usuario.getUsuario().length() > 150) {
            throw new ValidateException("El nombre de usuario no puede exceder los 150 caracteres");
        }

        // Validar contraseña
        if (usuario.getContrasenia() == null || usuario.getContrasenia().isEmpty()) {
            throw new ValidateException("La contraseña es requerida");
        }

        // Validar tipo de usuario
        if (usuario.getTipoUsuario() == null) {
            throw new ValidateException("El tipo de usuario es requerido");
        }
    }
}
