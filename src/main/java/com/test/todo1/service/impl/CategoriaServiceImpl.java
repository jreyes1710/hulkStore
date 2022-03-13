package com.test.todo1.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.todo1.entities.Categoria;
import com.test.todo1.repositories.CategoriaRepository;

@Service
public class CategoriaServiceImpl {

	@Autowired
	private CategoriaRepository categoriaRepo;

	public List<Categoria> listarCategorias() {
		return categoriaRepo.findAll();
	}
	
	public Categoria obtenerCategoriaPorId(Long idCategoria) {
		Optional<Categoria> categoria = categoriaRepo.findById(idCategoria);
		return categoria.get();
	}

	public void crearCategoria(Categoria categoria) {
		categoriaRepo.save(categoria);
	}
}
