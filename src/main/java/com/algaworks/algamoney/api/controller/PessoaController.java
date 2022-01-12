package com.algaworks.algamoney.api.controller;

import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> buscarTodos(){
        return pessoaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pessoa> criar( @RequestBody  @Valid Pessoa pessoa, HttpServletResponse response){
        pessoa = pessoaRepository.save(pessoa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(pessoa.getCodigo()).toUri();

        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(pessoa);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pessoa> buscarPorCodigo(@PathVariable Integer codigo){

        Pessoa pessoa = pessoaRepository.findById(codigo).orElse(null);

        if(pessoa == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(pessoa);
    }
}
