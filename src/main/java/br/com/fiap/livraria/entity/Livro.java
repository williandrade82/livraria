package br.com.fiap.livraria.entity;

import br.com.fiap.livraria.dto.LivroDTO;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_LIVRO")
public class Livro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "preco")
    private BigDecimal preco;

    @Column(name = "autor")
    private String autor;

    public Long getId() {
        return id;
    }

    public Livro() {}

    public Livro(LivroDTO livroDTO){
        setId(livroDTO.getId());
        setAutor(livroDTO.getAutor());
        setIsbn(livroDTO.getIsbn());
        setPreco(livroDTO.getPreco());
        setTitulo(livroDTO.getTitulo());
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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
