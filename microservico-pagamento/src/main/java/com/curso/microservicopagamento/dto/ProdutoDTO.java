package com.curso.microservicopagamento.dto;

import com.curso.microservicopagamento.entity.Produto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id", "estoque"})
@Data
@EqualsAndHashCode(callSuper = false)
public class ProdutoDTO extends RepresentationModel<ProdutoDTO> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer estoque;

    public static ProdutoDTO cadastrar(Produto produto) {
        return new ModelMapper().map(produto, ProdutoDTO.class);
    }
}

