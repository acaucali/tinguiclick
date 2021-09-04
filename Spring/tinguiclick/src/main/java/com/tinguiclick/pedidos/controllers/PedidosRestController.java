package com.tinguiclick.pedidos.controllers;

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

import com.tinguiclick.pedidos.model.Factura;
import com.tinguiclick.pedidos.model.Pedido;
import com.tinguiclick.pedidos.service.IFacturaService;
import com.tinguiclick.pedidos.service.IPedidosService;
import com.tinguiclick.service.IAliadosService;
import com.tinguiclick.service.IDomiciliariosService;



@CrossOrigin(origins= {"http://localhost:4200","*"})
@RestController
@RequestMapping("/api/tinguiclick")
public class PedidosRestController {
	
	//Interfaces de crud service
	
	@Autowired
	private IPedidosService pedidosService;
		
	@Autowired
	private IAliadosService aliadosService;
	
	@Autowired
	private IDomiciliariosService domiciliariosService;
	
	@Autowired
	private IFacturaService facturaService;
	
	//Servicios Rest tabla - Tipo Identificacion 
	
	private final Logger log = LoggerFactory.getLogger(PedidosRestController.class);
	
	//servicio que trae la lista de tipos de identificacion
	@GetMapping("/pedidos")
	public List<Pedido> index (){
		return pedidosService.findAll();
	}
	
	@GetMapping("/pedidos/filtro/{desde, hasta}")
	public List<Pedido> pedidosFiltro(@PathVariable Date desde, @PathVariable Date hasta){		
		return pedidosService.findByFechas(desde, hasta);
	}
	
	@GetMapping("/pedidos/factura/filtro/{desde, hasta}")
	public List<Factura> facturasFiltro(@PathVariable Date desde, @PathVariable Date hasta){		
		return facturaService.findByFechas(desde, hasta);
	}
		
	//servicio que muestra un tipo de identificacion
	@GetMapping("/pedidos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Pedido pedidoId=null;
		Map<String, Object> response = new HashMap<>();
		
		try { 
			pedidoId= pedidosService.findById(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(pedidoId == null) {
		  response.put("mensaje", "El pedido Id: ".concat(id.toString().concat(" no existe en la base de datos!"))); 	
		  return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Pedido>(pedidoId, HttpStatus.OK); 		
	}
	
	//servicio que crea un tipo de identificacion
	@PostMapping("/pedidos")
	public ResponseEntity<?> create(@Valid @RequestBody Pedido pedidoN, BindingResult result) {
		
		Pedido pedidoNew= null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors= result.getFieldErrors().stream().map(err ->
				"Campo: "+err.getField()+" "+err.getDefaultMessage()
			).collect(Collectors.toList());
			
			response.put("errors", errors);
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try { 
			pedidoN.setFechaRegistro(new Date());
			pedidoN.setAlerta("verde");
			 
			
			pedidoNew= pedidosService.save(pedidoN);

		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El pedido ha sido creado con Exito!");
		response.put("pedido", pedidoNew);
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.CREATED);
	}
	
	//servicio que actualiza un tipo de identificacion
	@PutMapping("/pedidos/{id}")
	public ResponseEntity<?>  update(@Valid @RequestBody Pedido pedido, BindingResult result, @PathVariable Long id) {
		Pedido pedidoActual= pedidosService.findById(id);
		Pedido pedidoUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors= result.getFieldErrors().stream().map(err ->
				"Campo: "+err.getField()+" "+err.getDefaultMessage()
			).collect(Collectors.toList());
			
			response.put("errors", errors);
		    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(pedidoActual == null) {
			  response.put("mensaje", "Error, no se pudo editar, el pedido ID: ".concat(id.toString().concat(" no existe en la base de datos!"))); 	
			  return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try{
				
			pedidoActual.setAlerta(pedido.getAlerta());
			pedidoActual.setAliado(pedido.getAliado());
			pedidoActual.setApellidoCliente(pedido.getApellidoCliente());
			pedidoActual.setCiudad(pedido.getCiudad());
			pedidoActual.setDetalle(pedido.getDetalle());
			pedidoActual.setDireccionCliente(pedido.getDireccionCliente());
			pedidoActual.setDomiciliario(pedido.getDomiciliario());
			pedidoActual.setEstado(pedido.getEstado());
			pedidoActual.setMetodoPago(pedido.getMetodoPago());
			pedidoActual.setMunicipio(pedido.getMunicipio());
			pedidoActual.setNombreCliente(pedido.getNombreCliente());
			pedidoActual.setNumeroCelular(pedido.getNumeroCelular());
			pedidoActual.setObservacion(pedido.getObservacion());
			pedidoActual.setTarifa(pedido.getTarifa());
			pedidoActual.setTelefono(pedido.getTelefono());
			pedidoActual.setTipo(pedido.getTipo());
			pedidoActual.setValor(pedido.getValor());
			pedidoActual.setFechaModificacion(new Date());
																			
			pedidoUpdated=pedidosService.save(pedidoActual);
		
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el pedido en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El pedido ha sido actualizado con Exito!");
		response.put("pedido", pedidoUpdated);
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.CREATED);
	}
	
	//servicio que recibe el pedido
		@PutMapping("/pedidos/recibido/{id}")
		public ResponseEntity<?>  recibirPedido(@Valid @RequestBody Pedido pedido, BindingResult result, @PathVariable Long id) {
			Pedido pedidoActual= pedidosService.findById(id);
			Pedido pedidoUpdated = null;
			Map<String, Object> response = new HashMap<>();
			
			if(result.hasErrors()) {
				
				List<String> errors= result.getFieldErrors().stream().map(err ->
					"Campo: "+err.getField()+" "+err.getDefaultMessage()
				).collect(Collectors.toList());
				
				response.put("errors", errors);
			    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
			
			if(pedidoActual == null) {
				  response.put("mensaje", "Error, no se pudo editar, el pedido ID: ".concat(id.toString().concat(" no existe en la base de datos!"))); 	
				  return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			           
			try{
					
				pedidoActual.setAlerta("amarillo");
				pedidoActual.setFechaModificacion(new Date());	
				pedidoActual.setEstado((byte) 2);
				
				pedidoUpdated=pedidosService.save(pedidoActual);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el pedido en la base de datos!");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("mensaje", "El pedido ha sido actualizado con Exito!");
			response.put("pedido", pedidoUpdated);
			return new ResponseEntity<Map<String, Object>> (response,HttpStatus.CREATED);
		}
		
		//servicio que entrega el pedido 
		@PutMapping("/pedidos/entregado/{id}")
		public ResponseEntity<?>  entregarPedido(@Valid @RequestBody Pedido pedido, BindingResult result, @PathVariable Long id) {
			Pedido pedidoActual= pedidosService.findById(id);
			Pedido pedidoUpdated = null;
			Factura factura = new Factura();
			
			Map<String, Object> response = new HashMap<>();
			
			if(result.hasErrors()) {
				
				List<String> errors= result.getFieldErrors().stream().map(err ->
					"Campo: "+err.getField()+" "+err.getDefaultMessage()
				).collect(Collectors.toList());
				
				response.put("errors", errors);
			    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}
			
			if(pedidoActual == null) {
				  response.put("mensaje", "Error, no se pudo editar, el pedido ID: ".concat(id.toString().concat(" no existe en la base de datos!"))); 	
				  return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			try{
				
				// registrar factura
				factura.setAliadoId(pedidoActual.getAliado());
				factura.setDomiciliarioId(pedidoActual.getDomiciliario());
				factura.setFechaRegistro(new Date());
				factura.setObservacion(pedidoActual.getObservacion());
				factura.setTipo(pedidoActual.getTipo());
				factura.setUbicacion(pedidoActual.getDireccionCliente());
				factura.setValor(Double.parseDouble(pedidoActual.getValor()));
				factura.setTarifaId(pedidoActual.getTarifa());
				
				facturaService.save(factura);
				
				// actualizar pedido
				pedidoActual.setAlerta("rojo");
				pedidoActual.setEstado((byte) 3);
				pedidoActual.setFechaModificacion(new Date());
																				
				pedidoUpdated=pedidosService.save(pedidoActual);
						
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el pedido en la base de datos!");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("mensaje", "El pedido ha sido actualizado con Exito!");
			response.put("pedido", pedidoUpdated);
			return new ResponseEntity<Map<String, Object>> (response,HttpStatus.CREATED);
		}
	
	
	//servicio que elimina el pedido
	@DeleteMapping("/pedidos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try{
			
			pedidosService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el pedido en la base de datos!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El pedido ha sido eliminado con Exito!");
		return new ResponseEntity<Map<String, Object>> (response,HttpStatus.OK);
	}
	
}
