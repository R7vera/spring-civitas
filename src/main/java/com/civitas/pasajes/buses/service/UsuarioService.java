package com.civitas.pasajes.buses.service;

import java.util.List;


import com.civitas.pasajes.buses.entity.Usuario;

public interface UsuarioService {

	public List<Usuario> findAll();
    // Buscar País por ID
	public Usuario findById(int id);

    // Guardar un nuevo País
    public Usuario save(Usuario pais);

    // Eliminar un País por ID
    public void delete(int id);

}
