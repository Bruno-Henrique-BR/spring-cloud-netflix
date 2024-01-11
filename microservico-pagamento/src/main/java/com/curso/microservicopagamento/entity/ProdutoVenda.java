package com.curso.microservicopagamento.entity;

import com.curso.microservicopagamento.dto.ProdutoVendaDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;
import javax.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "produto_venda")
@Data
public class ProdutoVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_produto", nullable = false, length = 10)
    private Long idProduto;

    @Column(name = "quantidade", nullable = false, length = 10)
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venda")
    private Venda venda;

    public static ProdutoVenda cadastrar(ProdutoVendaDTO produtoVendaDTO){
        return new ModelMapper().map(produtoVendaDTO, ProdutoVenda.class);
    }
}