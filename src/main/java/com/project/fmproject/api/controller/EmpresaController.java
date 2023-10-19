package com.project.fmproject.api.controller;

import com.project.fmproject.domain.model.PostoContagem;
import com.project.fmproject.domain.repository.PostoContagemRepository;
import com.project.fmproject.domain.service.PostoContagemService;
import com.project.fmproject.domain.specification.EmpresaSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    PostoContagemService postoContagemService;

    @Autowired
    PostoContagemRepository postoContagemRepository;




//    @GetMapping("/buscarPorNome")
//    public List<PostoContagem> buscarPorNome(@RequestParam String nome) {
//        return postoContagemRepository.findByNomeContainingIgnoreCase(nome);
//    }


//    @GetMapping("/buscarPorCnpj")
//    public ResponseEntity<PostoContagem> buscarPorCnpj(@RequestParam String cnpj) {
//        Optional<PostoContagem> empresaOptional = postoContagemRepository.findByCnpj(cnpj);
//        if (empresaOptional.isPresent()) {
//            return ResponseEntity.ok(empresaOptional.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping
    public ResponseEntity<Page<PostoContagem>> listarEmpresas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PostoContagem> empresas = postoContagemService.listarEmpresas(pageable);
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/lista-empresas")
    public ResponseEntity<List<PostoContagem>> listarEmpresas() {
        List<PostoContagem> empresas = postoContagemService.listPostoContagem();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostoContagem> buscarEmpresaPorId(@PathVariable Long id) {
        PostoContagem empresa = postoContagemService.buscarPostoContagemPorId(id);
        return ResponseEntity.ok(empresa);
    }

    @PostMapping
    public ResponseEntity<PostoContagem> salvarEmpresa(@RequestBody PostoContagem empresa) {
        PostoContagem empresaSalva = postoContagemService.salvarEmpresa(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostoContagem> atualizarEmpresa(@PathVariable Long id, @RequestBody PostoContagem empresaAtualizada) {
        PostoContagem empresa = postoContagemService.atualizarEmpresa(id, empresaAtualizada);
        return ResponseEntity.ok(empresa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable Long id) {
        postoContagemService.deletarEmpresa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pesquisar")
    public Page<PostoContagem> filtrarEmpresas(@RequestParam(value = "nome", required = false) String nome,
                                               @RequestParam(value = "cnpj", required = false) String cnpj,
                                               @RequestParam(value = "razaoSocial", required = false) String razaoSocial,
                                               Pageable pageable ) {
        Specification<PostoContagem> specification = EmpresaSpecification.filtrarEmpresas(nome, cnpj, razaoSocial);
        return postoContagemRepository.findAll(specification, pageable);
    }
}