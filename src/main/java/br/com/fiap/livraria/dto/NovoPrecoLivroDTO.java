package br.com.fiap.livraria.dto;

import java.math.BigDecimal;

public class NovoPrecoLivroDTO {

    private BigDecimal preco;

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
