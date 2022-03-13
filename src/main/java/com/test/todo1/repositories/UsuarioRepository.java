package com.test.todo1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.todo1.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>  {

	Usuario findOneByUsername(String username);	
}
