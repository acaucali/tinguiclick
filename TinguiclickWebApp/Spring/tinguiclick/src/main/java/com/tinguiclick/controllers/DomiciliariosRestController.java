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

import com.tinguiclick.model.Domiciliarios;
import com.tinguiclick.service.IDomiciliariosService;


@CrossOrigin(origins= {"http://localhost:4200","*"})
@RestController
@RequestMapping("/api/tinguiclick")
public class DomiciliariosRestController {
	
	//Interfaces de crud service
	
	@Autowired
	private IDomiciliariosService domiciliariosService;
		
	
	//Servicios Rest tabla - Tipo Identificacion 
	
	private final Logger log = LoggerFactory.getLogger(DomiciliariosRestController.class);
	
	//servicio que trae la lista de tipos de identificacion
	@GetMapping("/domiciliarios")
	public List<Domiciliarios> index (){
		return domiciliariosService.findAll();
	}
		
	//servicio que muestra un tipo de identificacion
	@GetMapping("/domiciliarios/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Domiciliarios domiciliarioId=null;
		Map<String, Object> response = new HashMap<>();
		
		try { 
			domiciliarioId= domiciliariosService.findById(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(domiciliarioId == null) {
		  response.put("mensaje", "El domiciliario Id: ".concat(id.toString().concat(" no existe en la base de datos!"))); 	
		  return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Domiciliarios>(domiciliarioId, HttpStatus.OK); 		
	}
	
	//servicio que crea un tipo de identificacion
	@PostMapping("/domiciliarios")
	public ResponseEntity<?> create(@Valid @RequestBody Domiciliarios domicilioN, BindingResult result) {
		
		Domiciliarios domicilioNew= null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors= result.getFieldErrors().stream().map(err ->
				"Campo: "+err.getField()+" "+err.getDefaultMessage()
			).collect(Collectors.toList());
			
			response.put("errors", errors);
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try { 
			
			domicilioNew= domiciliariosService.save(domicilioN);

		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El domiciliario ha sido creado con Exito!");
		response.put("domiciliario", domicilioNew);
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.CREATED);
	}
	
	//servicio que actualiza un tipo de identificacion
	@PutMapping("/domiciliarios/{id}")
	public ResponseEntity<?>  update(@Valid @RequestBody Domiciliarios domiciliario, BindingResult result, @PathVariable Long id) {
		Domiciliarios domiciliarioActual= domiciliariosService.findById(id);
		Domiciliarios domiciliariodUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors= result.getFieldErrors().stream().map(err ->
				"Campo: "+err.getField()+" "+err.getDefaultMessage()
			).collect(Collectors.toList());
			
			response.put("errors", errors);
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(domiciliarioActual == null) {
			  response.put("mensaje", "Error, no se pudo editar, el domiciliario ID: ".concat(id.toString().concat(" no existe en la base de datos!"))); 	
			  return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try{
			
			domiciliarioActual.setApellidos(domiciliario.getApellidos());
			domiciliarioActual.setArriendo(domiciliario.getArriendo());
			domiciliarioActual.setDireccionHogar(domiciliario.getDireccionHogar());
			domiciliarioActual.setDuracionArriendo(domiciliario.getDuracionArriendo());
			domiciliarioActual.setDiasDisponibilidad(domiciliario.getDiasDisponibilidad());
			domiciliarioActual.setEps(domiciliario.getEps());
			domiciliarioActual.setPension(domiciliario.getPension());
			domiciliarioActual.setArl(domiciliario.getArl());
			domiciliarioActual.setGrupoSanguineo(domiciliario.getGrupoSanguineo());
			domiciliarioActual.setHorarioDisponibilidad(domiciliario.getHorarioDisponibilidad());
			domiciliarioActual.setIdentificacion(domiciliario.getIdentificacion());
			domiciliarioActual.setNombres(domiciliario.getNombres());
			domiciliarioActual.setPasaporte(domiciliario.getPasaporte());
			domiciliarioActual.setTelefono(domiciliario.getTelefono());
			domiciliarioActual.setTipoIdentificacion(domiciliario.getTipoIdentificacion());
			domiciliarioActual.setUsuarioId(domiciliario.getUsuarioId());
			domiciliarioActual.setDocumentoId(domiciliario.getDocumentoId());
			domiciliarioActual.setCuentaBancaria(domiciliario.getCuentaBancaria());
			domiciliarioActual.setCuentaDaviplata(domiciliario.getCuentaDaviplata());
			domiciliarioActual.setCuentaNequi(domiciliario.getCuentaNequi());
			domiciliarioActual.setTipoCuentaBancaria(domiciliario.getTipoCuentaBancaria());
			domiciliarioActual.setNombreBanco(domiciliario.getNombreBanco());
													
			domiciliariodUpdated=domiciliariosService.save(domiciliarioActual);
			
		
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el domiciliario en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El domiciliario ha sido actualizado con Exito!");
		response.put("domiciliario", domiciliariodUpdated);
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.CREATED);
	}
	
	//servicio que elimina el tipo de identificacion
	@DeleteMapping("/domiciliarios/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try{
			
			domiciliariosService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el domiciliario en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El domiciliario ha sido eliminado con Exito!");
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.OK);
	}
	
}
