package com.tinguiclick.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tinguiclick.model.RegistroAdministrativo;
import com.tinguiclick.service.IRegistroAdministrativoService;


@CrossOrigin(origins= {"http://localhost:4200","*"})
@RestController
@RequestMapping("/api/tinguiclick")
public class RegistroAdministrativoRestController {
	
	//Interfaces de crud service
	
	@Autowired
	private IRegistroAdministrativoService registrosService;
		
	
	//Servicios Rest tabla - registro administrativo
	
	private final Logger log = LoggerFactory.getLogger(RegistroAdministrativoRestController.class);
	
	//servicio que trae la lista de tipos de identificacion
	@GetMapping("/registro")
	public List<RegistroAdministrativo> index (){
		return registrosService.findAll();
	}
		
	//servicio que muestra un registro administrativo
	@GetMapping("/registro/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		RegistroAdministrativo registroId=null;
		Map<String, Object> response = new HashMap<>();
		
		try { 
			registroId= registrosService.findById(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(registroId == null) {
		  response.put("mensaje", "El registro Id: ".concat(id.toString().concat(" no existe en la base de datos!"))); 	
		  return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RegistroAdministrativo>(registroId, HttpStatus.OK); 		
	}
	
	//servicio que crea un registro administrativo
	@PostMapping("/registro")
	public ResponseEntity<?> create(@Valid @RequestBody RegistroAdministrativo registroN, BindingResult result) {
		
		RegistroAdministrativo registroNew= null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors= result.getFieldErrors().stream().map(err ->
				"Campo: "+err.getField()+" "+err.getDefaultMessage()
			).collect(Collectors.toList());
			
			response.put("errors", errors);
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try { 
			
			registroNew= registrosService.save(registroN);

		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El registro ha sido creado con Exito!");
		response.put("aliado", registroNew);
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.CREATED);
	}
	
	//servicio que actualiza un registro administrativo
	@PutMapping("/registro/{id}")
	public ResponseEntity<?>  update(@Valid @RequestBody RegistroAdministrativo registro, BindingResult result, @PathVariable Long id) {
		RegistroAdministrativo registroActual= registrosService.findById(id);
		RegistroAdministrativo registroUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors= result.getFieldErrors().stream().map(err ->
				"Campo: "+err.getField()+" "+err.getDefaultMessage()
			).collect(Collectors.toList());
			
			response.put("errors", errors);
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(registroActual == null) {
			  response.put("mensaje", "Error, no se pudo editar, el registro ID: ".concat(id.toString().concat(" no existe en la base de datos!"))); 	
			  return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try{
			
			registroActual.setDescripcion(registro.getDescripcion());
			registroActual.setFechaModificacion(new Date());
			registroActual.setNumeroHoras(registro.getNumeroHoras());
		
																			
			registroUpdated=registrosService.save(registroActual);
		
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el registro en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El registro ha sido actualizado con Exito!");
		response.put("registro", registroUpdated);
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.CREATED);
	}
	
	//servicio que elimina el registro administrativo
	@DeleteMapping("/registro/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try{
			
			registrosService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el registro en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El registro ha sido eliminado con Exito!");
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.OK);
	}
	
}
