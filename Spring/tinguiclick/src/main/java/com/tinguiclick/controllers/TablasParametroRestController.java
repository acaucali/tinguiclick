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

import com.tinguiclick.model.TipoIdentificacion;
import com.tinguiclick.service.ITipoIdentificacionService;

@CrossOrigin(origins= {"http://localhost:4200","*"})
@RestController
@RequestMapping("/api/tinguiclick")
public class TablasParametroRestController {
	
	//Interfaces de crud service
	
	@Autowired
	private ITipoIdentificacionService tipoIdentificacionService;
		
	
	//Servicios Rest tabla - Tipo Identificacion 
	
	private final Logger log = LoggerFactory.getLogger(TablasParametroRestController.class);
	
	//servicio que trae la lista de tipos de identificacion
	@GetMapping("/tipoIdentificacion")
	public List<TipoIdentificacion> index (){
		return tipoIdentificacionService.findAll();
	}
		
	//servicio que muestra un tipo de identificacion
	@GetMapping("/tipoIdentificacion/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		TipoIdentificacion tipoId=null;
		Map<String, Object> response = new HashMap<>();
		
		try { 
			tipoId= tipoIdentificacionService.findById(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(tipoId == null) {
		  response.put("mensaje", "El tipo identificacion Id: ".concat(id.toString().concat(" no existe en la base de datos!"))); 	
		  return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TipoIdentificacion>(tipoId, HttpStatus.OK); 		
	}
	
	//servicio que crea un tipo de identificacion
	@PostMapping("/tipoIdentificacion")
	public ResponseEntity<?> create(@Valid @RequestBody TipoIdentificacion tipoIdN, BindingResult result) {
		
		TipoIdentificacion tipoIdNew= null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors= result.getFieldErrors().stream().map(err ->
				"Campo: "+err.getField()+" "+err.getDefaultMessage()
			).collect(Collectors.toList());
			
			response.put("errors", errors);
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try { 
			
			tipoIdNew= tipoIdentificacionService.save(tipoIdN);

		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El tipo de identificación ha sido creado con Exito!");
		response.put("tipoId", tipoIdNew);
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.CREATED);
	}
	
	//servicio que actualiza un tipo de identificacion
	@PutMapping("/tipoIdentificacion/{id}")
	public ResponseEntity<?>  update(@Valid @RequestBody TipoIdentificacion tipoId, BindingResult result, @PathVariable Long id) {
		TipoIdentificacion tipoIdActual= tipoIdentificacionService.findById(id);
		TipoIdentificacion tipoIdUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors= result.getFieldErrors().stream().map(err ->
				"Campo: "+err.getField()+" "+err.getDefaultMessage()
			).collect(Collectors.toList());
			
			response.put("errors", errors);
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(tipoIdActual == null) {
			  response.put("mensaje", "Error, no se pudo editar, el tipo identificación ID: ".concat(id.toString().concat(" no existe en la base de datos!"))); 	
			  return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try{
			tipoIdActual.setDescripcion(tipoId.getDescripcion());
			tipoIdActual.setNombre(tipoId.getNombre());
									
			tipoIdUpdated=tipoIdentificacionService.save(tipoIdActual);
		
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el tipo identificación en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El tipo identificación ha sido actualizado con Exito!");
		response.put("tipoId", tipoIdUpdated);
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.CREATED);
	}
	
	//servicio que elimina el tipo de identificacion
	@DeleteMapping("/tipoIdentificacion/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try{
			
			tipoIdentificacionService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el tipo identificación en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El tipo de identificación ha sido eliminado con Exito!");
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.OK);
	}
	
}
