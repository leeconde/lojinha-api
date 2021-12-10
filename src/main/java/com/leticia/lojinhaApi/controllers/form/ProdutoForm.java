package com.leticia.lojinhaApi.controllers.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.leticia.lojinhaApi.model.Produto;
import com.leticia.lojinhaApi.repositories.ProdutoRepository;

public class ProdutoForm {

	@NotNull
	@NotEmpty
	@Length(min = 5, max = 100)
	private String nome;

	private Double valor;

	@NotNull
	@NotEmpty
	@Length(min = 5, max = 500)
	private String descricao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Produto converter(ProdutoRepository repository) {
		Produto produto = repository.findOneByNome(getNome());
		return new Produto(nome, valor, descricao);
	}

	public Produto atualizar(Long id, ProdutoRepository produtoRepository) {
		Produto produto = produtoRepository.getOne(id);
		produto.setNome(this.nome);
		produto.setValor(this.valor);
		produto.setDescricao(this.descricao);
		return produto;
	}

}
