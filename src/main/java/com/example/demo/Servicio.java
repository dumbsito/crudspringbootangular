package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

	
	@Autowired
	Repositorio repo;
	
	public List<Entidad>listarTodos(){
		return repo.findAll();
	}
	
	public boolean eliminarEntidad(Long id) {
		try {
		repo.deleteById(id);
		return true;
	}catch(Exception e) {
		return false;
	}
}
}
