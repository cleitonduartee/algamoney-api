package com.algaworks.algamoney.api.controller;

import com.algaworks.algamoney.api.event.RecursoCriadoEvent;
import com.algaworks.algamoney.api.model.dto.CategoriaDTO;
import com.algaworks.algamoney.api.model.entity.Categoria;
import com.algaworks.algamoney.api.repository.CategoriaRepository;
import com.algaworks.algamoney.api.service.ICategoriaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private ICategoriaService service;
    private ApplicationEventPublisher publisher;

    @Autowired
    public CategoriaController(ICategoriaService service, ApplicationEventPublisher publisher) {
        this.service = service;
        this.publisher = publisher;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> buscarTodos(){
        List<Categoria> categoriaList = this.service.buscarTodos();
        List<CategoriaDTO> categoriaDTOList = categoriaList.stream()
                .map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList());
        return ResponseEntity.ok(categoriaDTOList);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<CategoriaDTO> buscarPorCodigo(@PathVariable Integer codigo){
        Categoria categoria = this.service.buscarPorCodigo(codigo);
        return ResponseEntity.ok(new CategoriaDTO(categoria));
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> cadastrarCategoria( @RequestBody  @Valid CategoriaDTO categoriaDto, HttpServletResponse response){
        Categoria categoria = service.cadastrar(service.converteCategoriaDtoParaCategoriaEntity(categoriaDto));
        publisher.publishEvent(new RecursoCriadoEvent(this,response,categoria.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new CategoriaDTO(categoria));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCategoria(@PathVariable Integer codigo){
        this.service.deletar(codigo);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<CategoriaDTO> atualizarCategoria(@RequestBody @Valid CategoriaDTO categoriaDTO, @PathVariable Integer codigo){
        Categoria categoria = this.service.atualizar(codigo,categoriaDTO);
        BeanUtils.copyProperties(categoria, categoriaDTO);
        return ResponseEntity.ok(categoriaDTO);
    }
}
