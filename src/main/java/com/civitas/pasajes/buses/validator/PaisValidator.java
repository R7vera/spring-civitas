package com.civitas.pasajes.buses.validator;

import com.civitas.pasajes.buses.entity.Pais;
import com.civitas.pasajes.buses.exception.ValidateException;

public class PaisValidator {

    public static void save(Pais pais) {
        // Validar nombre
        if (pais.getNombre() == null || pais.getNombre().isEmpty()) {
            throw new ValidateException("El nombre del país es requerido");
        }

        // Validar longitud del nombre (máximo 50 caracteres)
        if (pais.getNombre().length() > 80) {
            throw new ValidateException("El nombre del país no puede exceder los 80 caracteres");
        }
        
        if (pais.getUrl().length() > 150) {
            throw new ValidateException("El nombre del país no puede exceder los 80 caracteres");
        }
    }
}
