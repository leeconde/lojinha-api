package com.leticia.lojinhaApi.controllers.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.leticia.lojinhaApi.model.Produto;

public class ProdutoDto {

	private Long id;

	private String nome;

	private Double valor;

	private String descricao;

	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.descricao = produto.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Double getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public static List<ProdutoDto> convert(List<Produto> produtos) {
		return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}

}
