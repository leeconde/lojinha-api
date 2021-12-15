package com.leticia.lojinhaApi.controllers.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.leticia.lojinhaApi.model.Usuario;
import com.leticia.lojinhaApi.repositories.UsuarioRepository;

public class UsuarioForm {

	@NotNull
	@NotEmpty
	private String login;

	@NotNull
	@NotEmpty
	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario converter(UsuarioRepository repository) {
		Usuario usuario = repository.findByLogin(getLogin());
		return new Usuario(login, senha);
	}

	public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getById(id);
		usuario.setLogin(this.login);
		usuario.setSenha(this.senha);
		return usuario;
	}

}
