package br.com.fiap.livraria.service;

import br.com.fiap.livraria.dto.LivroDTO;
import br.com.fiap.livraria.dto.NovoLivroDTO;
import br.com.fiap.livraria.dto.NovoPrecoLivroDTO;

import java.util.List;

public interface LivrariaService {

    List<LivroDTO> listarLivros(String titulo);
    LivroDTO buscarLivroPorId(Long id);
    LivroDTO criar(NovoLivroDTO novoLivroDTO);
    LivroDTO atualizar(Long id, NovoLivroDTO novoLivroDTO);
    LivroDTO atualizarPreco(Long id, NovoPrecoLivroDTO novoPrecoLivroDTO);
    void deletarLivro(Long id);
}
