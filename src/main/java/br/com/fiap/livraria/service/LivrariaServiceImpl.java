package br.com.fiap.livraria.service;

import br.com.fiap.livraria.dto.LivroDTO;
import br.com.fiap.livraria.dto.NovoLivroDTO;
import br.com.fiap.livraria.dto.NovoPrecoLivroDTO;
import org.apache.el.lang.ELArithmetic;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class LivrariaServiceImpl implements LivrariaService {

    private List<LivroDTO> listaModelo = new ArrayList();

    public LivrariaServiceImpl(){
        LivroDTO lv1 = new LivroDTO();
        lv1.setIsbn("111");
        lv1.setPreco(BigDecimal.valueOf(70.25));
        lv1.setAutor("José da Silva");
        lv1.setTitulo("As maravilhas do primeiro livro");

        LivroDTO lv2 = new LivroDTO();
        lv2.setIsbn("222");
        lv2.setPreco(BigDecimal.valueOf(170.25));
        lv2.setAutor("Carlos Silveira");
        lv2.setTitulo("Da Caatinga ao Mangue");

        LivroDTO lv3 = new LivroDTO();
        lv3.setIsbn("333");
        lv3.setPreco(BigDecimal.valueOf(234.25));
        lv3.setAutor("Ana Melissa");
        lv3.setTitulo("O Romance de José");

        listaModelo.add(lv1);
        listaModelo.add(lv2);
        listaModelo.add(lv3);

    }

    @Override
    public List<LivroDTO> listarLivros(String titulo) {
        List<LivroDTO> livrosRetorno = new ArrayList();
        for (LivroDTO livro: listaModelo
             ) {
            if (livro.getTitulo().contains(titulo))
                livrosRetorno.add(livro);
        }
        return livrosRetorno;
    }

    @Override
    public LivroDTO buscarLivroPorId(Long id) {
        for (LivroDTO livro: listaModelo
        ) {
            if (livro.getId().equals(id))
            return livro;
        }
        return new LivroDTO();
    }

    @Override
    public LivroDTO criar(NovoLivroDTO novoLivroDTO) {
        LivroDTO novoLivro = new LivroDTO();
        novoLivro.setId(Long.valueOf(listaModelo.size()));
        novoLivro.setTitulo(novoLivroDTO.getTitulo());
        novoLivro.setPreco(novoLivroDTO.getPreco());
        novoLivro.setAutor(novoLivroDTO.getAutor());
        novoLivro.setIsbn(novoLivro.getId()+""+novoLivro.getId()+""+novoLivro.getId()+"");
        listaModelo.add(novoLivro);
        return novoLivro;
    }

    @Override
    public LivroDTO atualizar(Long id, NovoLivroDTO novoLivroDTO) {
        LivroDTO update = buscarLivroPorId(id);
        update.setPreco(novoLivroDTO.getPreco());
        update.setTitulo(novoLivroDTO.getTitulo());
        update.setAutor(novoLivroDTO.getAutor());
        return update;
    }

    @Override
    public LivroDTO atualizarPreco(Long id, NovoPrecoLivroDTO novoPrecoLivroDTO) {
        LivroDTO update = buscarLivroPorId(id);
        update.setPreco(novoPrecoLivroDTO.getPreco());
        return update;
    }

    @Override
    public void deletarLivro(Long id) {
        LivroDTO del = buscarLivroPorId(id);
        listaModelo.remove(del);
    }
}
