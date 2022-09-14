package br.com.fiap.livraria.dto;

import br.com.fiap.livraria.entity.Livro;

import java.math.BigDecimal;

public class LivroDTO {

    public LivroDTO(NovoLivroDTO novoLivroDTO) {
        updateAll(novoLivroDTO);
    }

    public LivroDTO(Livro livro){
        setPreco(livro.getPreco());
        setId(livro.getId());
        setIsbn(livro.getIsbn());
        setAutor(livro.getAutor());
        setTitulo(livro.getTitulo());
    }
    
    public LivroDTO updateAll(NovoLivroDTO novoLivroDTO) {
        setIsbn(novoLivroDTO.getIsbn());
        setTitulo(novoLivroDTO.getTitulo());
        setPreco(novoLivroDTO.getPreco());
        setAutor(novoLivroDTO.getAutor());
        return this;
    }

    public LivroDTO updatePreco(NovoPrecoLivroDTO novoPrecoLivroDTO){
        setPreco(novoPrecoLivroDTO.getPreco());
        return this;
    }



    private Long id;

    private String isbn;
    private String titulo;
    private String autor;
    private BigDecimal preco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

}
