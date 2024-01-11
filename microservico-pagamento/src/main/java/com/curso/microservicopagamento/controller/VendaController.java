package com.curso.microservicopagamento.controller;

import com.curso.microservicopagamento.dto.VendaDTO;
import com.curso.microservicopagamento.service.VendaService;
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
@RequestMapping("/venda")
public class VendaController {
    private final VendaService vendaService;

    private final PagedResourcesAssembler<VendaDTO> assembler;

    @Autowired
    public VendaController(VendaService vendaService, PagedResourcesAssembler<VendaDTO> assembler) {
        this.vendaService = vendaService;
        this.assembler = assembler;
    }

    @GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
    public VendaDTO consultar(@PathVariable("id")Long id){
        VendaDTO vendaDTO = vendaService.consultar(id);
        vendaDTO.add(linkTo(methodOn(VendaController.class).consultar(id)).withSelfRel());
        return vendaDTO;
    }
    @GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> listar(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "limit", defaultValue = "12") int limit,
                                    @RequestParam(value = "direction", defaultValue = "asc") String direction)
    {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection,"data"));
        Page<VendaDTO> vendas = vendaService.listar(pageable);
        vendas.stream()
                .forEach(p -> p.add(linkTo(methodOn(VendaController.class).consultar(p.getId())).withSelfRel()));
        PagedModel<EntityModel<VendaDTO>> pagedModel = assembler.toModel(vendas);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json","application/xml","application/x-yaml"},
            consumes = {"application/json","application/xml","application/x-yaml"})
    public VendaDTO cadastrar(@RequestBody VendaDTO vendaDTO){
        VendaDTO vendDTO = vendaService.cadastrar(vendaDTO);
        vendDTO.add(linkTo(methodOn(VendaController.class).consultar(vendDTO.getId())).withSelfRel());
        return vendDTO;
    }
}
