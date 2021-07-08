package com.tinguiclick.controllers;

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

import com.tinguiclick.model.Tarifa;
import com.tinguiclick.service.ITarifaService;


@CrossOrigin(origins= {"http://localhost:4200","*"})
@RestController
@RequestMapping("/api/tinguiclick")
public class TarifaRestController {
	
	//Interfaces de crud service
	
	@Autowired
	private ITarifaService tarifaService;
		
	
	//Servicios Rest tabla - Tipo Identificacion 
	
	private final Logger log = LoggerFactory.getLogger(TarifaRestController.class);
	
	//servicio que trae la lista de tipos de identificacion
	@GetMapping("/tarifa")
	public List<Tarifa> index (){
		return tarifaService.findAll();
	}
		
	//servicio que muestra un tipo de identificacion
	@GetMapping("/tarifa/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Tarifa tarifaId=null;
		Map<String, Object> response = new HashMap<>();
		
		try { 
			tarifaId= tarifaService.findById(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(tarifaId == null) {
		  response.put("mensaje", "La tarifa Id: ".concat(id.toString().concat(" no existe en la base de datos!"))); 	
		  return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Tarifa>(tarifaId, HttpStatus.OK); 		
	}
	
	//servicio que crea un tipo de identificacion
	@PostMapping("/tarifa")
	public ResponseEntity<?> create(@Valid @RequestBody Tarifa tarifaN, BindingResult result) {
		
		Tarifa tarifaNew= null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors= result.getFieldErrors().stream().map(err ->
				"Campo: "+err.getField()+" "+err.getDefaultMessage()
			).collect(Collectors.toList());
			
			response.put("errors", errors);
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try { 
			
			tarifaNew= tarifaService.save(tarifaN);

		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La tarifa ha sido creado con Exito!");
		response.put("tarifa", tarifaNew);
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.CREATED);
	}
	
	//servicio que actualiza un tipo de identificacion
	@PutMapping("/tarifa/{id}")
	public ResponseEntity<?>  update(@Valid @RequestBody Tarifa tarifa, BindingResult result, @PathVariable Long id) {
		Tarifa tarifaActual= tarifaService.findById(id);
		Tarifa tarifaUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors= result.getFieldErrors().stream().map(err ->
				"Campo: "+err.getField()+" "+err.getDefaultMessage()
			).collect(Collectors.toList());
			
			response.put("errors", errors);
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(tarifaActual == null) {
			  response.put("mensaje", "Error, no se pudo editar, la tarifa ID: ".concat(id.toString().concat(" no existe en la base de datos!"))); 	
			  return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try{
						
			tarifaActual.setUbicacion(tarifa.getUbicacion());
			tarifaActual.setValor(tarifa.getValor());
																
			tarifaUpdated=tarifaService.save(tarifaActual);
		
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la tarifa en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El tarifa ha sido actualizado con Exito!");
		response.put("tarifa", tarifaUpdated);
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.CREATED);
	}
	
	//servicio que elimina el tipo de identificacion
	@DeleteMapping("/tarifa/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try{
			
			tarifaService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar la tarifa en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La tarifa ha sido eliminado con Exito!");
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.OK);
	}
	
}
