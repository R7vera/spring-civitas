package com.civitas.pasajes.buses.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.civitas.pasajes.buses.entity.Departamento;



@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

	public Departamento findByNombre(String nombre);

}
