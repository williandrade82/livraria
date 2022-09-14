package br.com.fiap.livraria.repository;

import br.com.fiap.livraria.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivrariaRepository extends JpaRepository <Livro, Long> {

    List<Livro> findAllByTituloLike(String titulo);

    List<Livro> findAllByTituloContainingIgnoreCase(String parteTitulo);

    List<Livro> findAll();

    Livro findLivroById(Long id);



}
