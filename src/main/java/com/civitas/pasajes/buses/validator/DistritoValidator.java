package com.civitas.pasajes.buses.validator;

import com.civitas.pasajes.buses.entity.Distrito;
import com.civitas.pasajes.buses.exception.ValidateException;

public class DistritoValidator {
    public static void save(Distrito distrito) {
        // Validar nombre de la ciudad
        if (distrito.getNombre() == null || distrito.getNombre().trim().isEmpty()) {
            throw new ValidateException("El nombre de la distrito es requerido");
        }
        if (distrito.getNombre().length() > 50) {
            throw new ValidateException("El nombre de la distrito no debe exceder los 50 caracteres");
        }
        if (distrito.getUrlImg().length() > 150) {
            throw new ValidateException("El nombre del distrito no puede exceder los 80 caracteres");
        }

        // Validar país asignado
        if (distrito.getCiudad() == null || distrito.getCiudad().getId() <= 0) {
            throw new ValidateException("La distrito debe estar asignada a un ciudad válido");
        }
    }
}
