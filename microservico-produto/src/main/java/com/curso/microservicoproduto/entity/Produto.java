package com.curso.microservicoproduto.entity;


import com.curso.microservicoproduto.dto.ProdutoDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produto")
@Data
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
    @Column(name = "estoque", nullable = false,  length = 10)
    private Integer estoque;
    @Column(name = "preco", nullable = false,  length = 10)
    private Double preco;

    public static Produto cadastrar(ProdutoDTO produtoDTO) {
        return new ModelMapper().map(produtoDTO, Produto.class);
    }

}
