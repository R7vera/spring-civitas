package com.civitas.pasajes.buses.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.civitas.pasajes.buses.entity.Usuario;
import com.civitas.pasajes.buses.exception.GeneralException;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.exception.ValidateException;

import com.civitas.pasajes.buses.repository.UsuarioRepository;
import com.civitas.pasajes.buses.service.UsuarioService;

import com.civitas.pasajes.buses.validator.UsuarioValidator;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepository repository;


	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		try {
			List<Usuario> registros = repository.findAll();
			return registros;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		} catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}

	@Override
	public Usuario findById(int id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Usuario save(Usuario user) {
		try {

			UsuarioValidator.save(user);
			// Nuevo registro
			if (user.getId() == 0) {

				Usuario nuevo = repository.save(user);
				return nuevo;
			}
			
		
			// editar registro
			Usuario registro = repository.findById(user.getId())
					.orElseThrow(() -> new NoDataFoundException("No existe un registro con ese ID"));
			registro.setTipoUsuario(user.getTipoUsuario());
			registro.setContrasenia(user.getContrasenia());
			registro.setUsuario(user.getUsuario());
			repository.save(registro);
			return registro;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		} catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}

	@Override
	public void delete(int id) {
		// Verifica si el país existe
		Usuario registro = repository.findById(id)
				.orElseThrow(() -> new NoDataFoundException("No se encontró un país con el ID proporcionado"));

		// Elimina el país si existe
		repository.delete(registro);
	}
}
