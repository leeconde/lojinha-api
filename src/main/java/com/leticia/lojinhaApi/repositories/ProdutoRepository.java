package com.leticia.lojinhaApi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leticia.lojinhaApi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	List<Produto> findByNome(String nome);
	
	Produto findOneByNome(String nome);

}
