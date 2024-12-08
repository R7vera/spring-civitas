package com.civitas.pasajes.buses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.civitas.pasajes.buses.entity.TipoLicencia;

@Repository
public interface TipoLicenciaRepository  extends JpaRepository<TipoLicencia, Integer> {

}
