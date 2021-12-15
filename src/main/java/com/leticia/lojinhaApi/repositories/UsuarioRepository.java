package com.leticia.lojinhaApi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leticia.lojinhaApi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findOneByLogin(String login);

	List<Usuario> findByLogin(String login);

}
