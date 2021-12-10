package com.leticia.lojinhaApi.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.leticia.lojinhaApi.controllers.dto.ProdutoDto;
import com.leticia.lojinhaApi.controllers.form.ProdutoForm;
import com.leticia.lojinhaApi.model.Produto;
import com.leticia.lojinhaApi.repositories.ProdutoRepository;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<ProdutoDto> listar(String produtoNome) {
		if (produtoNome == null) {
			List<Produto> produto = produtoRepository.findAll();
			return ProdutoDto.convert(produto);
		} else {
			List<Produto> produto = produtoRepository.findByNome(produtoNome);
			return ProdutoDto.convert(produto);
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder) {
		Produto produto = form.converter(produtoRepository);
		produtoRepository.save(produto);

		URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDto(produto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> infoProduto(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			return ResponseEntity.ok(new ProdutoDto(produto.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoForm form) {
		Optional<Produto> optional = produtoRepository.findById(id);
		if (optional.isPresent()) {
			Produto produto = form.atualizar(id, produtoRepository);
			return ResponseEntity.ok(new ProdutoDto(produto));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Produto> optional = produtoRepository.findById(id);
		if (optional.isPresent()) {
			produtoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
