package com.civitas.pasajes.buses.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.civitas.pasajes.buses.entity.TipoDocumento;
import com.civitas.pasajes.buses.exception.GeneralException;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.exception.ValidateException;
import com.civitas.pasajes.buses.repository.TipoDocumentoRepository;
import com.civitas.pasajes.buses.service.TipoDocumentoService;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService{
	 @Autowired
	    private TipoDocumentoRepository repository;


	    @Override
	    public TipoDocumento findByNombre(String nombre) {
	        return repository.findByNombre(nombre);
	    }
	    
		@Override
		@Transactional(readOnly = true)
		public List<TipoDocumento> findAll() {
			try {
				List<TipoDocumento> registros = repository.findAll();
				return registros;
			} catch (ValidateException | NoDataFoundException e) {
				throw e;
			} catch (GeneralException e) {
				throw new GeneralException("Error del servidor");
			}
		}
}
