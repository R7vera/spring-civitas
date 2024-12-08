package com.civitas.pasajes.buses.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.civitas.pasajes.buses.entity.Departamento;
import com.civitas.pasajes.buses.exception.GeneralException;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.exception.ValidateException;
import com.civitas.pasajes.buses.repository.DepartamentoRepository;
import com.civitas.pasajes.buses.service.DepartamentoService;
import com.civitas.pasajes.buses.validator.DepartamentoValidator;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoRepository repository;


    @Override
    public Departamento findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }
    
	@Override
	@Transactional(readOnly = true)
	public List<Departamento> findAll() {
		try {
			List<Departamento> registros = repository.findAll();
			return registros;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		} catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}


    @Override
    public Departamento findById(int id) {
        return repository.findById(id).orElse(null);
    }

    
	@Override
	@Transactional
	public Departamento save(Departamento departamento) {
		try {

			DepartamentoValidator.save(departamento);
			// Nuevo registro
			if (departamento.getId() == 0) {
				
				Departamento nuevo = repository.save(departamento);				
				return nuevo;
			}
			// editar registro
			Departamento registro = repository.findById(departamento.getId())
					.orElseThrow(() -> new NoDataFoundException("No existe un registro con ese ID"));
			registro.setNombre(departamento.getNombre());
			registro.setUrlImg(departamento.getUrlImg());
			registro.setPais(departamento.getPais());
			repository.save(registro);
			return registro;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		} catch (GeneralException e) {
			throw new GeneralException("Error del servidor", e);
		}
	}

	@Override
	public void delete(int id) {
	    // Verifica si el país existe
		Departamento departamento = repository.findById(id)
	        .orElseThrow(() -> new NoDataFoundException("No se encontró un departamento con el ID proporcionado"));

	    // Elimina el país si existe
		repository.delete(departamento);
	}

}