package com.curso.microservicoproduto.controller;

import com.curso.microservicoproduto.dto.ProdutoDTO;
import com.curso.microservicoproduto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    private final PagedResourcesAssembler<ProdutoDTO> assembler;

    @Autowired
    public ProdutoController(ProdutoService produtoService, PagedResourcesAssembler<ProdutoDTO> assembler) {
        this.produtoService = produtoService;
        this.assembler = assembler;
    }

    @GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
    public ProdutoDTO consultar(@PathVariable("id")Long id){
        ProdutoDTO produtoDTO = produtoService.consultar(id);
        produtoDTO.add(linkTo(methodOn(ProdutoController.class).consultar(id)).withSelfRel());
        return produtoDTO;
    }
    @GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> listar(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "limit", defaultValue = "12") int limit,
                                    @RequestParam(value = "direction", defaultValue = "asc") String direction)
    {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection,"nome"));
        Page<ProdutoDTO> produtos = produtoService.listar(pageable);
        produtos.stream()
                .forEach(p -> p.add(linkTo(methodOn(ProdutoController.class).consultar(p.getId())).withSelfRel()));
        PagedModel<EntityModel<ProdutoDTO>> pagedModel = assembler.toModel(produtos);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json","application/xml","application/x-yaml"},
            consumes = {"application/json","application/xml","application/x-yaml"})
    public ProdutoDTO cadastrar(@RequestBody ProdutoDTO produtoDTO){
        ProdutoDTO prodDTO = produtoService.cadastrar(produtoDTO);
        prodDTO.add(linkTo(methodOn(ProdutoController.class).consultar(prodDTO.getId())).withSelfRel());
        return prodDTO;
    }

    @PutMapping(produces = {"application/json","application/xml","application/x-yaml"},
            consumes = {"application/json","application/xml","application/x-yaml"})
    public ProdutoDTO atualizar(@RequestBody ProdutoDTO produtoDTO){
        ProdutoDTO prodDTO = produtoService.atualizar(produtoDTO);
        prodDTO.add(linkTo(methodOn(ProdutoController.class).consultar(produtoDTO.getId())).withSelfRel());
        return prodDTO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable("id") Long id){
        produtoService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
