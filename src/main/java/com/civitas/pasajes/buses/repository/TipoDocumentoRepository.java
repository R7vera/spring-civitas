package com.civitas.pasajes.buses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.civitas.pasajes.buses.entity.TipoDocumento;
@Repository
public interface TipoDocumentoRepository  extends JpaRepository<TipoDocumento, Integer> {
	public TipoDocumento findByNombre(String nombre);
}
