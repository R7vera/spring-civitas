package com.civitas.pasajes.buses.service;

import com.civitas.pasajes.buses.entity.*;


public interface AuthService {
	public Usuario findByUsuarioOrCorreo(String nombre, String correo);
	public Usuario findByUsarioOrCorreoRegistro(String userorcorreo);
}
