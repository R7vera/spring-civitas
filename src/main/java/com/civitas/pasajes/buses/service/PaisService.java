package com.civitas.pasajes.buses.service;




import com.civitas.pasajes.buses.entity.Pais;

import java.util.List;



public interface PaisService {

    // Buscar País por nombre
	public Pais findByNombre(String nombre);
 
	public List<Pais> findAll();
    // Buscar País por ID
	public Pais findById(int id);

    // Guardar un nuevo País
    public Pais save(Pais pais);

    // Eliminar un País por ID
    public void delete(int id);
    
    

}
