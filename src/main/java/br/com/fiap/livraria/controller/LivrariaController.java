package br.com.fiap.livraria.controller;

import br.com.fiap.livraria.dto.LivroDTO;
import br.com.fiap.livraria.dto.NovoLivroDTO;
import br.com.fiap.livraria.dto.NovoPrecoLivroDTO;
import br.com.fiap.livraria.service.LivrariaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("livros")
public class LivrariaController {
    private LivrariaService livrariaService;


    public LivrariaController(LivrariaService livrariaService) {
        this.livrariaService = livrariaService;
    }

    @GetMapping
    public List<LivroDTO> listarLivros(
            @RequestParam(required = false) String titulo
    ){
        if (titulo == null) titulo = "";
        return livrariaService.listarLivros(titulo);
    }

    @GetMapping("{id}")
    public LivroDTO getLivroById(@PathVariable(name="id") Long id){
        return livrariaService.buscarLivroPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroDTO createLivro(
            @RequestBody NovoLivroDTO novoLivroDTO
    ){
        return livrariaService.criar(novoLivroDTO);
    }

    @PutMapping("{id}")
    public LivroDTO updateLivro(
            @RequestBody NovoLivroDTO novoLivroDTO,
            @PathVariable Long id
    ){
        return livrariaService.atualizar(id, novoLivroDTO);

    }


    @PatchMapping("{id}")
    public LivroDTO updatePrecoDoLivro(
            @RequestBody NovoPrecoLivroDTO novoPrecoDoLivroDTO,
            @PathVariable Long id
    ){
        return livrariaService.atualizarPreco(id, novoPrecoDoLivroDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLivro(
            @PathVariable Long id
    ) {
        livrariaService.deletarLivro(id);
    }

}
