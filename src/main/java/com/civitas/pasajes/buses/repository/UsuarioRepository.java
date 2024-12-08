package com.civitas.pasajes.buses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.civitas.pasajes.buses.entity.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>  {
	public Usuario findByUsuarioOrCorreo(String nombre, String correo);
}
