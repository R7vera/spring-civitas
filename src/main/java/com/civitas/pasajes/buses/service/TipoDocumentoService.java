package com.civitas.pasajes.buses.service;

import java.util.List;

import com.civitas.pasajes.buses.entity.TipoDocumento;

public interface TipoDocumentoService {
	public TipoDocumento findByNombre(String nombre);
	 
	public List<TipoDocumento> findAll();
}
