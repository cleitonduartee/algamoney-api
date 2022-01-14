package com.algaworks.algamoney.api.controller;

import com.algaworks.algamoney.api.event.RecursoCriadoEvent;
import com.algaworks.algamoney.api.model.dto.PessoaDTO;
import com.algaworks.algamoney.api.model.entity.Pessoa;
import com.algaworks.algamoney.api.service.IPessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private IPessoaService service;
    private ApplicationEventPublisher publisher;

    @Autowired
    public PessoaController(IPessoaService service, ApplicationEventPublisher publisher) {
        this.service = service;
        this.publisher = publisher;
    }

    @GetMapping
    public List<PessoaDTO> buscarTodos(){
        List<Pessoa> pessoasEntityList = service.buscarTodos();
        List<PessoaDTO> pessoaDTOList = pessoasEntityList.stream()
                .map(pessoaEntity-> new PessoaDTO(pessoaEntity)).collect(Collectors.toList());

        return pessoaDTOList;
    }
    @GetMapping("/{codigo}")
    public ResponseEntity<PessoaDTO> buscarPorCodigo(@PathVariable Integer codigo){

        Pessoa pessoaEntity = service.buscarPorCodigo(codigo);
        return ResponseEntity.ok(new PessoaDTO(pessoaEntity));
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> cadastrarPessoa(@RequestBody  @Valid PessoaDTO pessoaDTO, HttpServletResponse response){
        Pessoa pessoa = service.cadastrar(service.convertePessoaDtoParaPessoaEntity(pessoaDTO));
        publisher.publishEvent(new RecursoCriadoEvent(this, response,pessoa.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new PessoaDTO(pessoa));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPessoa(@PathVariable Integer codigo){
        service.deletar(codigo);
    }

    @PutMapping("/{codigo}")
    public PessoaDTO atualizarPessoa(@RequestBody  PessoaDTO pessoaDTO, @PathVariable Integer codigo){
        Pessoa pessoaSalva = service.atualizar(codigo, pessoaDTO);
        BeanUtils.copyProperties(pessoaSalva, pessoaDTO);
        return pessoaDTO;
    }

    @PutMapping("/{codigo}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarAtivoPessoa(@RequestBody  Boolean ativo, @PathVariable Integer codigo){
        service.atualizarAtivoPessoa(codigo, ativo);
    }
}
