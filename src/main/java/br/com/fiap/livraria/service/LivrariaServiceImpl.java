package br.com.fiap.livraria.service;

import br.com.fiap.livraria.dto.LivroDTO;
import br.com.fiap.livraria.dto.NovoLivroDTO;
import br.com.fiap.livraria.dto.NovoPrecoLivroDTO;
import br.com.fiap.livraria.entity.Livro;
import br.com.fiap.livraria.repository.LivrariaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivrariaServiceImpl implements LivrariaService {

    private LivrariaRepository livrariaRepository;

    public LivrariaServiceImpl(LivrariaRepository livrariaRepository) {
        this.livrariaRepository = livrariaRepository;
    }

    @Override
    public List<LivroDTO> listarLivros(String titulo) {
        List<Livro> livros;
        if (titulo == null || titulo == "")
            livros = livrariaRepository.findAll();
        else
            livros = livrariaRepository.findAllByTituloContainingIgnoreCase(titulo);
        System.out.println(String.format("Exibindo livros %s | Título = %s", livros.toString(), titulo));
        return livros
                .stream()
                .map(LivroDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public LivroDTO buscarLivroPorId(Long id) {
        return new LivroDTO(livrariaRepository.findLivroById(id));
    }

    @Override
    public LivroDTO criar(NovoLivroDTO novoLivroDTO) {
        return new LivroDTO(livrariaRepository.save(new Livro(new LivroDTO(novoLivroDTO))));
    }

    @Override
    public LivroDTO atualizar(Long id, NovoLivroDTO novoLivroDTO) {
        LivroDTO livroDTO = new LivroDTO(livrariaRepository.findLivroById(id));
        livroDTO.updateAll(novoLivroDTO);
        return new LivroDTO(livrariaRepository.saveAndFlush(new Livro(livroDTO)));
    }

    @Override
    public LivroDTO atualizarPreco(Long id, NovoPrecoLivroDTO novoPrecoLivroDTO) {

        return new LivroDTO(
                livrariaRepository.saveAndFlush(
                        new Livro(
                                new LivroDTO(
                                        livrariaRepository.findLivroById(id))
                                        .updatePreco(novoPrecoLivroDTO))
                ));
    }

    @Override
    public void deletarLivro(Long id) {
        livrariaRepository.delete(livrariaRepository.findLivroById(id));
    }
}
