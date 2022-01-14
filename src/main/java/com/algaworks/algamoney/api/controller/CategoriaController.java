package com.algaworks.algamoney.api.controller;

import com.algaworks.algamoney.api.event.RecursoCriadoEvent;
import com.algaworks.algamoney.api.model.entity.Categoria;
import com.algaworks.algamoney.api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Categoria> buscarTodos(){
        return categoriaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Categoria> criar( @RequestBody  @Valid Categoria categoria, HttpServletResponse response){
        categoria = categoriaRepository.save(categoria);
        publisher.publishEvent(new RecursoCriadoEvent(this,response,categoria.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Categoria> buscarPorCodigo(@PathVariable Integer codigo){

        Categoria cat = categoriaRepository.findById(codigo).orElse(null);

        if(cat == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(cat);
    }
}
