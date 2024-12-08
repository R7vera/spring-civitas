package com.civitas.pasajes.buses.validator;

import com.civitas.pasajes.buses.entity.Departamento;
import com.civitas.pasajes.buses.exception.ValidateException;

public class DepartamentoValidator {
    public static void save(Departamento departamento) {
        // Validar nombre
        if (departamento.getNombre() == null || departamento.getNombre().isEmpty()) {
            throw new ValidateException("El nombre del Departamento es requerido");
        }

        // Validar longitud del nombre (máximo 50 caracteres)
        if (departamento.getNombre().length() > 80) {
            throw new ValidateException("El nombre del Departamento no puede exceder los 80 caracteres");
        }
        
        if (departamento.getUrlImg().length() > 150) {
            throw new ValidateException("El nombre del Departamento no puede exceder los 80 caracteres");
        }
        
        if( departamento.getPais() == null) {
        	throw new ValidateException("Falta Asociar al Pais a Departamento");
        }
    }
}
