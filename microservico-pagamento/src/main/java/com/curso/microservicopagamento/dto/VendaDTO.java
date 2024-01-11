package com.curso.microservicopagamento.dto;

import com.curso.microservicopagamento.entity.ProdutoVenda;
import com.curso.microservicopagamento.entity.Venda;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonPropertyOrder({"id", "data", "produtos", "valorTotal"})
@Data
@EqualsAndHashCode(callSuper = false)
public class VendaDTO extends RepresentationModel<VendaDTO> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Date data;
    private List<ProdutoVendaDTO> produtos;
    private Double valorTotal;

    public static VendaDTO cadastrar(Venda venda) {
        return new ModelMapper().map(venda, VendaDTO.class);
    }
}

