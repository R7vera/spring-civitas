package com.civitas.pasajes.buses.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.civitas.pasajes.buses.entity.TipoLicencia;
import com.civitas.pasajes.buses.exception.GeneralException;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.exception.ValidateException;

import com.civitas.pasajes.buses.repository.TipoLicenciaRepository;

import com.civitas.pasajes.buses.service.TipoLicenciaService;

@Service
public class TipoLicenciaServiceImpl implements TipoLicenciaService {
	@Autowired
	private TipoLicenciaRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<TipoLicencia> findAll() {
		try {
			List<TipoLicencia> registros = repository.findAll();
			return registros;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		} catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}
}
