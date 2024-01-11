package com.curso.microservicopagamento.entity;

import com.curso.microservicopagamento.dto.ProdutoDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.*;


@Entity
@Table(name = "produto")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "estoque", nullable = false, length = 10)
    private Integer estoque;

    public static Produto cadastrar(ProdutoDTO produtoDTO){
        return new ModelMapper().map(produtoDTO, Produto.class);
    }
}
