package com.leticia.lojinhaApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leticia.lojinhaApi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByLogin(String login);

}
