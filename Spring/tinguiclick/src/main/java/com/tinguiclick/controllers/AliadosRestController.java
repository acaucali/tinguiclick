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

import com.tinguiclick.model.Aliados;
import com.tinguiclick.service.IAliadosService;


@CrossOrigin(origins= {"http://localhost:4200","*"})
@RestController
@RequestMapping("/api/tinguiclick")
public class AliadosRestController {
	
	//Interfaces de crud service
	
	@Autowired
	private IAliadosService aliadosService;
		
	
	//Servicios Rest tabla - Tipo Identificacion 
	
	private final Logger log = LoggerFactory.getLogger(AliadosRestController.class);
	
	//servicio que trae la lista de tipos de identificacion
	@GetMapping("/aliados")
	public List<Aliados> index (){
		return aliadosService.findAll();
	}
		
	//servicio que muestra un tipo de identificacion
	@GetMapping("/aliados/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Aliados domiciliarioId=null;
		Map<String, Object> response = new HashMap<>();
		
		try { 
			domiciliarioId= aliadosService.findById(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(domiciliarioId == null) {
		  response.put("mensaje", "El aliado Id: ".concat(id.toString().concat(" no existe en la base de datos!"))); 	
		  return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Aliados>(domiciliarioId, HttpStatus.OK); 		
	}
	
	//servicio que crea un tipo de identificacion
	@PostMapping("/aliados")
	public ResponseEntity<?> create(@Valid @RequestBody Aliados aliadoN, BindingResult result) {
		
		Aliados aliadoNew= null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors= result.getFieldErrors().stream().map(err ->
				"Campo: "+err.getField()+" "+err.getDefaultMessage()
			).collect(Collectors.toList());
			
			response.put("errors", errors);
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try { 
			
			aliadoNew= aliadosService.save(aliadoN);

		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El aliado ha sido creado con Exito!");
		response.put("aliado", aliadoNew);
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.CREATED);
	}
	
	//servicio que actualiza un tipo de identificacion
	@PutMapping("/aliados/{id}")
	public ResponseEntity<?>  update(@Valid @RequestBody Aliados aliado, BindingResult result, @PathVariable Long id) {
		Aliados aliadoActual= aliadosService.findById(id);
		Aliados aliadoUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors= result.getFieldErrors().stream().map(err ->
				"Campo: "+err.getField()+" "+err.getDefaultMessage()
			).collect(Collectors.toList());
			
			response.put("errors", errors);
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(aliadoActual == null) {
			  response.put("mensaje", "Error, no se pudo editar, el aliado ID: ".concat(id.toString().concat(" no existe en la base de datos!"))); 	
			  return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try{
			
			aliadoActual.setCategoriaPrincipal(aliado.getCategoriaPrincipal());
			aliadoActual.setCuentaBancaria(aliado.getCuentaBancaria());
			aliadoActual.setCuentaDaviplata(aliado.getCuentaDaviplata());
			aliadoActual.setCuentaNequi(aliado.getCuentaNequi());
			aliadoActual.setDireccionFactura(aliado.getDireccionFactura());
			aliadoActual.setEmailFactura(aliado.getEmailFactura());
			aliadoActual.setNit(aliado.getNit());
			aliadoActual.setNombre(aliado.getNombre());
			aliadoActual.setRazonSocial(aliado.getRazonSocial());
			aliadoActual.setTelefono(aliado.getTelefono());
			aliadoActual.setDocumentoId(aliado.getDocumentoId());
			aliadoActual.setTipoCuentaBancaria(aliado.getTipoCuentaBancaria());
			aliadoActual.setNombreBanco(aliado.getNombreBanco());
																
			aliadoUpdated=aliadosService.save(aliadoActual);
		
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el aliado en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El aliado ha sido actualizado con Exito!");
		response.put("aliado", aliadoUpdated);
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.CREATED);
	}
	
	//servicio que elimina el tipo de identificacion
	@DeleteMapping("/aliados/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try{
			
			aliadosService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el aliado en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El aliado ha sido eliminado con Exito!");
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.OK);
	}
	
}
