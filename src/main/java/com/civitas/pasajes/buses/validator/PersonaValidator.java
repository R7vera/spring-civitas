package com.civitas.pasajes.buses.validator;

import com.civitas.pasajes.buses.entity.Persona;
import com.civitas.pasajes.buses.exception.ValidateException;

public class PersonaValidator {

    public static void save(Persona persona) {
        // Validar nombres
        if (persona.getNombres() == null || persona.getNombres().trim().isEmpty()) {
            throw new ValidateException("El nombre es requerido");
        }

        // Validar apellido paterno
        if (persona.getApellidoPaterno() == null || persona.getApellidoPaterno().trim().isEmpty()) {
            throw new ValidateException("El apellido paterno es requerido");
        }

        // Validar apellido materno
        if (persona.getApellidoMaterno() == null || persona.getApellidoMaterno().trim().isEmpty()) {
            throw new ValidateException("El apellido materno es requerido");
        }

        // Validar documento de identidad
        if (persona.getDocumentoIdentidad() == null || persona.getDocumentoIdentidad().trim().isEmpty()) {
            throw new ValidateException("El documento de identidad es requerido");
        }

        // Validar tipo de documento
        if (persona.getTipoDocumento() == null) {
            throw new ValidateException("El tipo de documento es requerido");
        }
    }
}
