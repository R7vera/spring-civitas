package com.civitas.pasajes.buses.validator;

import com.civitas.pasajes.buses.entity.Ciudad;
import com.civitas.pasajes.buses.exception.ValidateException;

public class CiudadValidator {

    public static void save(Ciudad ciudad) {
        // Validar nombre de la ciudad
        if (ciudad.getNombre() == null || ciudad.getNombre().trim().isEmpty()) {
            throw new ValidateException("El nombre de la ciudad es requerido");
        }
        if (ciudad.getNombre().length() > 50) {
            throw new ValidateException("El nombre de la ciudad no debe exceder los 50 caracteres");
        }
        if (ciudad.getUrlImg().length() > 150) {
            throw new ValidateException("El nombre del Departamento no puede exceder los 80 caracteres");
        }

        // Validar país asignado
        if (ciudad.getDepartamento() == null || ciudad.getDepartamento().getId() <= 0) {
            throw new ValidateException("La ciudad debe estar asignada a un departamento válido");
        }
    }
}
