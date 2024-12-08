package com.civitas.pasajes.buses.validator;

import com.civitas.pasajes.buses.entity.TipoDocumento;
import com.civitas.pasajes.buses.exception.ValidateException;

public class TipoDocumentoValidator {

    public static void save(TipoDocumento tipoDocumento) {
        // Validar nombre
        if (tipoDocumento.getNombre() == null || tipoDocumento.getNombre().isEmpty()) {
            throw new ValidateException("El nombre del tipo de documento es requerido");
        }

        // Validar longitud del nombre
        if (tipoDocumento.getNombre().length() > 50) {
            throw new ValidateException("El nombre del tipo de documento no puede exceder los 50 caracteres");
        }
    }
}
