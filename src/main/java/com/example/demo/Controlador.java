package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins="http://localhost:4200/")
public class Controlador {

	@Autowired
	Servicio servi;
	
	@Autowired
	Repositorio repo;
	
	@GetMapping("/empleados")
	public List<Entidad>listarTodos(){
	return	servi.listarTodos();
	}
	
	@PostMapping("/empleados")
	public ResponseEntity<Entidad>post(@RequestBody Entidad e1){
		return ResponseEntity.ok(repo.save(e1));
	}
	
	@GetMapping("/empleados/{id}")
	public ResponseEntity<Entidad>getPorId(@PathVariable Long id){
		Entidad e1=repo.findById(id)
	              .orElseThrow(()->new ResourceNotFoundException(
	            		  ("No existe e l empleado con el ID:" + id)));
		
		return ResponseEntity.ok(e1);
	}
	
	@PutMapping("/empleados/{id}")
	public ResponseEntity<Entidad>actualizarEmpleado(@PathVariable Long id,@RequestBody Entidad detalles){
		Entidad e1=repo.findById(id)
	              .orElseThrow(()->new ResourceNotFoundException(
	            		  ("No existe el empleado con el ID:" + id)));
		e1.setNombre(detalles.getNombre());
		e1.setApellido(detalles.getApellido());
		e1.setEmail(detalles.getEmail());
		Entidad e2=repo.save(e1);
		return ResponseEntity.ok(e2);
	}
	
	@DeleteMapping("/empleados/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarEmpleado(@PathVariable Long id){
		Entidad empleado = repo.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID : " + id));
		
		repo.delete(empleado);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	
}
}
