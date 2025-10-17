package com.generattion.games.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generattion.games.model.Produto;
import com.generattion.games.repository.CategoriaRepository;
import com.generattion.games.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository; // Injetado para validar a existência da Categoria

    // 1. findAll() - Retorna todos os Produtos
    @GetMapping
    public ResponseEntity<List<Produto>> getAll(){
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    // 2. findById(id) - Retorna um Produto específico
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id){
        return produtoRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    // 3. findAllByNomeContainingIgnoreCase(nome) - Retorna Produtos por parte do nome (Exemplo de busca personalizada)
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome){
        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    // 4. post(@RequestBody) - Cria um novo Produto (com validação da Categoria)
    @PostMapping
    public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto){
        // Verifica se a Categoria existe antes de salvar o Produto
        if (categoriaRepository.existsById(produto.getCategoria().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(produtoRepository.save(produto));

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada!", null);
    }

    // 5. put(@RequestBody) - Atualiza um Produto existente (com validação da Categoria)
    @PutMapping
    public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto){
        // Verifica se o Produto a ser atualizado existe
        if (produtoRepository.existsById(produto.getId())) {

            // Verifica se a Categoria existe antes de salvar
            if (categoriaRepository.existsById(produto.getCategoria().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(produtoRepository.save(produto));

            // Se o Produto existe, mas a Categoria não
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada!", null);
        }
        
        // Se o Produto não existe
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    // 6. delete(id) - Deleta um Produto por ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        
        if(produto.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        
        produtoRepository.deleteById(id);                
    }
    
 // Desafio 1: Buscar produtos maiores que valor X, em ordem crescente
    @GetMapping("/maiorque/{preco}")
    public ResponseEntity<List<Produto>> getProdutosMaiorQue(@PathVariable BigDecimal preco) {
        return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanOrderByPrecoAsc(preco));
    }

    // Desafio 2: Buscar produtos menores que valor X, em ordem decrescente
    @GetMapping("/menorque/{preco}")
    public ResponseEntity<List<Produto>> getProdutosMenorQue(@PathVariable BigDecimal preco) {
        return ResponseEntity.ok(produtoRepository.findByPrecoLessThanOrderByPrecoDesc(preco));
    }
}