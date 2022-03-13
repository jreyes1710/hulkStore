package com.test.todo1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.todo1.entities.Categoria;
import com.test.todo1.exceptions.ModeloNotFoundException;
import com.test.todo1.service.impl.CategoriaServiceImpl;

import io.swagger.annotations.Api;

@Api
@RestController
public class CategoriaController {

	@Autowired
	private CategoriaServiceImpl categoriaservice;

	@GetMapping("/categoria/{idCategoria}")
	public ResponseEntity<Categoria> findById(@PathVariable Long idCategoria) {
		Categoria categoria = categoriaservice.obtenerCategoriaPorId(idCategoria);
		if (categoria == null) {
			throw new ModeloNotFoundException("No Existe Categoria con ID: " + idCategoria);

		}
		return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
	}

	@GetMapping("/listarCategorias")
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> listarCategorias = categoriaservice.listarCategorias();
		if (listarCategorias.isEmpty()) {
			throw new ModeloNotFoundException("No Existe data Cargada");

		}
		return new ResponseEntity<List<Categoria>>(listarCategorias, HttpStatus.OK);
	}

	@PostMapping("/crearCategorias")
	public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
		categoriaservice.crearCategoria(categoria);
		return new ResponseEntity<Categoria>(categoria, HttpStatus.CREATED);
	}

}
