package com.leticia.lojinhaApi.controllers.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.leticia.lojinhaApi.model.Usuario;

public class UsuarioDto {

	private Long id;

	private String login;

	private String senha;

	private boolean admin;

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.login = usuario.getLogin();
		this.senha = usuario.getSenha();
		this.admin = usuario.isAdmin();
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public boolean isAdmin() {
		return admin;
	}

	public static List<UsuarioDto> convert(List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}

}
