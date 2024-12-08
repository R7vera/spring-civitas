package com.civitas.pasajes.buses.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.civitas.pasajes.buses.converter.*;
import com.civitas.pasajes.buses.dto.PaisDto;
import com.civitas.pasajes.buses.entity.Pais;
import com.civitas.pasajes.buses.exception.NoDataFoundException;
import com.civitas.pasajes.buses.service.PaisService;
import com.civitas.pasajes.buses.util.WrapperResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/paises")
public class PaisController {

    @Autowired
    private PaisService paisService;

	@Autowired
	private PaisConverter converter;
 
    @GetMapping
	public ResponseEntity<List<PaisDto>> findAll(){				
		List<PaisDto> registros = converter.fromEntity(paisService.findAll());		
		return new WrapperResponse(true, "success", registros).createResponse(HttpStatus.OK);
	}
	
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable("nombre") String nombre) {
        try {
           
            Pais pais = paisService.findByNombre(nombre);

            if (pais == null) {
                throw new NoDataFoundException("No se encontró un país con el nombre proporcionado");
            }

           
            return new WrapperResponse(true, "success", pais).createResponse(HttpStatus.OK);

        } catch (NoDataFoundException e) {
          
            return new WrapperResponse(false, e.getMessage(), null).createResponse(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
           
            return new WrapperResponse(false, "Ocurrió un error interno", null).createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

  
	@PostMapping
	public ResponseEntity<PaisDto> create (@RequestBody PaisDto pais){
		Pais paisEntity=converter.fromDTO(pais);
		PaisDto registro = converter.fromEntity(paisService.save(paisEntity));
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.CREATED);
	}
  
    @PutMapping("/{id}")
    public ResponseEntity<Pais> update(@PathVariable("id") int id, @RequestBody Pais pais) {
        pais.setId(id);  
        Pais updatedPais = paisService.save(pais);
        return new WrapperResponse(true, "success", updatedPais).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        try {
            
            paisService.delete(id);
            return new WrapperResponse(true, "País eliminado correctamente", null).createResponse(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            
            return new WrapperResponse(false, "No se puede eliminar el país porque está siendo utilizado", null)
                    .createResponse(HttpStatus.CONFLICT);
        } catch (NoDataFoundException e) {
            
            return new WrapperResponse(false, e.getMessage(), null).createResponse(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            
            return new WrapperResponse(false, "Ocurrió un error interno", null).createResponse(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


 
    @GetMapping("/{id}")
    public ResponseEntity<Pais> findById(@PathVariable("id") int id) {
        Pais pais = paisService.findById(id);
        return new WrapperResponse(true, "success", pais).createResponse(HttpStatus.OK);
    }


}
