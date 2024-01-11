package com.curso.microservicopagamento.service;

import com.curso.microservicopagamento.dto.VendaDTO;
import com.curso.microservicopagamento.entity.ProdutoVenda;
import com.curso.microservicopagamento.entity.Venda;
import com.curso.microservicopagamento.exception.ResourceNotFoundException;
import com.curso.microservicopagamento.repository.ProdutoVendaRepository;
import com.curso.microservicopagamento.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {
    private final VendaRepository vendaRepository;
    private final ProdutoVendaRepository produtoVendaRepository;
    @Autowired
    public VendaService(VendaRepository vendaRepository, ProdutoVendaRepository produtoVendaRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoVendaRepository = produtoVendaRepository;
    }

    public VendaDTO cadastrar(VendaDTO vendaDTO){
        Venda venda = vendaRepository.save(Venda.cadastrar(vendaDTO));

        List<ProdutoVenda> produtosSalvos = new ArrayList<>();
        vendaDTO.getProdutos().forEach(p -> {
            ProdutoVenda pv = ProdutoVenda.cadastrar(p);
            pv.setVenda(venda);
            produtosSalvos.add(produtoVendaRepository.save(pv));
        });
        venda.setProdutos(produtosSalvos);
        return VendaDTO.cadastrar(venda);
    }

    public Page<VendaDTO> listar(Pageable pageable){
        var page = vendaRepository.findAll(pageable);
        return page.map(this::convertToVendaDTO);
    }

    private VendaDTO convertToVendaDTO(Venda venda) {
        return VendaDTO.cadastrar(venda);
    }

    public VendaDTO consultar(Long id) {
        var entity = vendaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        return VendaDTO.cadastrar(entity);
    }

    public void excluir(Long id ){
        var entity = vendaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada"));
        vendaRepository.delete(entity);
    }
}
