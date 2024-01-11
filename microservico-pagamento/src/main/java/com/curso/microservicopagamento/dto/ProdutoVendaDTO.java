package com.curso.microservicopagamento.dto;

import com.curso.microservicopagamento.entity.ProdutoVenda;
import com.curso.microservicopagamento.entity.Venda;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id", "idProduto", "quantidade"})
@Data
@EqualsAndHashCode(callSuper = false)
public class ProdutoVendaDTO extends RepresentationModel<ProdutoVendaDTO> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long idProduto;
    private Integer quantidade;

    public static ProdutoVendaDTO cadastrar(ProdutoVenda produtoVenda) {
        return new ModelMapper().map(produtoVenda, ProdutoVendaDTO.class);
    }
}

