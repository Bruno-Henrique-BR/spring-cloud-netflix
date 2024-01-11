package com.curso.microservicoproduto.service;

import com.curso.microservicoproduto.entity.Produto;
import com.curso.microservicoproduto.exception.ResourceNotFoundException;
import com.curso.microservicoproduto.message.ProdutoSendMessage;
import com.curso.microservicoproduto.repository.ProdutoRepository;
import com.curso.microservicoproduto.dto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final ProdutoSendMessage produtoSendMessage;
    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, ProdutoSendMessage produtoSendMessage) {
        this.produtoRepository = produtoRepository;
        this.produtoSendMessage = produtoSendMessage;
    }

    public ProdutoDTO cadastrar(ProdutoDTO produtoDTO){
     ProdutoDTO produtoRetorno = ProdutoDTO.cadastrar(produtoRepository.save(Produto.cadastrar(produtoDTO)));
     produtoSendMessage.sendMessage(produtoDTO);
        return produtoRetorno;
    }

    public Page<ProdutoDTO> listar(Pageable pageable){
        var page = produtoRepository.findAll(pageable);
        return page.map(this::convertToProdutoDTO);
    }

    private ProdutoDTO convertToProdutoDTO(Produto produto) {
        return ProdutoDTO.cadastrar(produto);
    }

    public ProdutoDTO consultar(Long id) {
        var entity = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        return ProdutoDTO.cadastrar(entity);
    }

    public ProdutoDTO atualizar(ProdutoDTO produtoDTO) {
        final Optional<Produto> optionalProduto = produtoRepository.findById(produtoDTO.getId());
        {
            if (!optionalProduto.isPresent()){
                new ResourceNotFoundException("Produto não encontrado");
            }
            return ProdutoDTO.cadastrar(produtoRepository.save(Produto.cadastrar(produtoDTO)));
        }
    }

    public void excluir(Long id ){
        var entity = produtoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
        produtoRepository.delete(entity);
    }
}
