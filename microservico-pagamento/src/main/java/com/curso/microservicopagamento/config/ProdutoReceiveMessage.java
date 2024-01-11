package com.curso.microservicopagamento.config;

import com.curso.microservicopagamento.dto.ProdutoDTO;
import com.curso.microservicopagamento.entity.Produto;
import com.curso.microservicopagamento.repository.ProdutoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProdutoReceiveMessage {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoReceiveMessage(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @RabbitListener(queues = {"${produto.rabbitmq.queue}"})
    public void receive(@Payload ProdutoDTO produtoDTO){
        produtoRepository.save(Produto.cadastrar(produtoDTO));
    }
}
