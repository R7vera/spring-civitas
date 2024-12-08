package com.civitas.pasajes.buses.rest;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civitas.pasajes.buses.converter.AuthUserConverter;
import com.civitas.pasajes.buses.converter.PersonaConverter;
import com.civitas.pasajes.buses.converter.TipoDocumentoConverter;
import com.civitas.pasajes.buses.converter.UsuarioConverter;
import com.civitas.pasajes.buses.dto.AuthUserDto;


import com.civitas.pasajes.buses.dto.TipoDocumentoDto;
import com.civitas.pasajes.buses.dto.UsuarioDto;

import com.civitas.pasajes.buses.entity.Persona;
import com.civitas.pasajes.buses.entity.Usuario;
import com.civitas.pasajes.buses.jwt.JwtService;
import com.civitas.pasajes.buses.service.*;
import com.civitas.pasajes.buses.service.AuthService;
import com.civitas.pasajes.buses.util.WrapperResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthUserConverter converter;
    
    @Autowired
    private TipoDocumentoConverter converterTipo;

    @Autowired
    private UsuarioConverter converterUsuario;
    
    @Autowired
    private PersonaService personaService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private TipoDocumentoService serviceTipo;
    
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    

    @GetMapping("/tipoDocumento")
	public ResponseEntity<List<TipoDocumentoDto>> findAll(){				
		List<TipoDocumentoDto> registros = converterTipo.fromEntity(serviceTipo.findAll());		
		return new WrapperResponse(true, "success", registros).createResponse(HttpStatus.OK);
	}
 
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> authRequest) {
        try {
        	String username = authRequest.get("username");
            String password = authRequest.get("password");
            // Llamamos al servicio para buscar el usuario y validar la contraseña
            Usuario user = authService.findByUsuarioOrCorreo(username, password);
            
            AuthUserDto registro = converter.fromEntity(user);
            
            String token = jwtService.generateToken(registro);
            Date expi = jwtService.extractExpiration(token);
            registro.setToken(token);
            registro.setDuracionTk(expi);
            // Si el usuario es encontrado y las credenciales son válidas, retornamos los datos
            return new WrapperResponse(true, "Login exitoso", registro).createResponse(HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            // Manejo de la excepción estándar para credenciales inválidas
            return new WrapperResponse(false, e.getMessage(), null).createResponse(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            // Manejo general de errores
            return new WrapperResponse(false, "Ocurrió un error interno", null).createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioDto> registrar(@RequestBody Usuario entity) {
        try {
            // Verificar si la persona ya está registrada
            Persona personaExistente = personaService.finByDocumentoIdentidad(entity.getPersona().getDocumentoIdentidad());
            if (personaExistente != null) {
                return new WrapperResponse(false, "Ya existe una cuenta vinculada", null).createResponse(HttpStatus.BAD_REQUEST);
            }

            // Verificar si el usuario ya está registrado
            Usuario usuarioExistente = authService.findByUsarioOrCorreoRegistro(entity.getUsuario());
            if (usuarioExistente != null) {
                return new WrapperResponse(false, "Ya existe una cuenta vinculada", null).createResponse(HttpStatus.BAD_REQUEST);
            }
            
            Persona registroPersona = entity.getPersona();
            
            registroPersona = personaService.save(registroPersona);
            entity.setPersona(registroPersona);
        	String encodedPassword = passwordEncoder.encode(entity.getContrasenia());
        	entity.setContrasenia(encodedPassword);
            UsuarioDto registro = converterUsuario.fromEntity(usuarioService.save(entity));
            registro.setContrasenia("************");
            // Si el usuario es registrado con éxito, retornamos los datos
            return new WrapperResponse(true, "Usuario exitoso", registro).createResponse(HttpStatus.OK);

        } catch (Exception e) {
            // Manejo general de errores con detalles adicionales
            e.printStackTrace(); // Opcional: imprime la traza del error en los logs
            String errorMessage = "Ocurrió un error interno: " + e.getMessage();
            return new WrapperResponse(false, errorMessage, null).createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
