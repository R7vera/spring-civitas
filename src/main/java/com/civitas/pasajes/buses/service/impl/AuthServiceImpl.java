package com.civitas.pasajes.buses.service.impl;



import com.civitas.pasajes.buses.entity.Usuario;
import com.civitas.pasajes.buses.repository.UsuarioRepository;
import com.civitas.pasajes.buses.service.AuthService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl  implements AuthService  {


    @Autowired
    private UsuarioRepository repository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public Usuario findByUsuarioOrCorreo(String userorpass, String password) {
    	Usuario user = repository.findByUsuarioOrCorreo(userorpass, userorpass);
    	
    	
    	if(user == null ) {
    		 throw new IllegalArgumentException("No hay usuario o Correo válidos"); 
    	}
  
    	 // Si el usuario no existe o la contraseña no coincide
        if (!passwordEncoder.matches(password, user.getContrasenia())) {
            throw new IllegalArgumentException("Credenciales inválidas");  // Aquí se usa una excepción estándar
        }

        return user;
    	
    }
    
    @Override 
    public Usuario findByUsarioOrCorreoRegistro(String userorcorreo) {
    	Usuario user = repository.findByUsuarioOrCorreo(userorcorreo, userorcorreo);
    	return user;
    }
}
