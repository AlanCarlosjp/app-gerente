package com.ms.product.dto;


import com.ms.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String nome;
    private Double altura;
    private Double largura;
    private String descricao;
    private Double preco;

    public ProductDto(Product entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.altura = entity.getAltura();
        this.largura = entity.getLargura();
        this.descricao = entity.getDescricao();
        this.preco = entity.getPreco();
    }
}
