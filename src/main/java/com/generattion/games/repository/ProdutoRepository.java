package com.generattion.games.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generattion.games.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	public List<Produto> findByPrecoGreaterThanOrderByPrecoAsc(BigDecimal preco);
	
	public List<Produto> findByPrecoLessThanOrderByPrecoDesc(BigDecimal preco);
	
	List<Produto> findAllByNomeContainingIgnoreCase (String nome);

}
