package com.algaworks.algamoney.api.controller;

import com.algaworks.algamoney.api.event.RecursoCriadoEvent;
import com.algaworks.algamoney.api.model.dto.LancamentoDTO;
import com.algaworks.algamoney.api.model.dto.LancamentoRequestDTO;
import com.algaworks.algamoney.api.model.entity.Categoria;
import com.algaworks.algamoney.api.model.entity.Lancamento;
import com.algaworks.algamoney.api.model.entity.Pessoa;
import com.algaworks.algamoney.api.service.ICategoriaService;
import com.algaworks.algamoney.api.service.ILancamentoService;
import com.algaworks.algamoney.api.service.IPessoaService;
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
@RequestMapping("/lancamentos")
public class LancamentoController {


    private ILancamentoService serviceLancamento;
    private ApplicationEventPublisher publisher;

    @Autowired
    public LancamentoController(ILancamentoService serviceLancamento,ApplicationEventPublisher publisher) {
        this.serviceLancamento = serviceLancamento;
        this.publisher = publisher;
    }

    @GetMapping
    public ResponseEntity<List<LancamentoDTO>> buscarTodos(){
        List<Lancamento> lancamentoList = serviceLancamento.buscarTodos();
        List<LancamentoDTO> lancamentoDTOList = lancamentoList.stream()
                .map(lacamento -> new LancamentoDTO(lacamento) ).collect(Collectors.toList());

        return ResponseEntity.ok(lancamentoDTOList);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<LancamentoDTO> buscarPorCodigo(@PathVariable Integer codigo){
        Lancamento lancamento = serviceLancamento.buscarPorCodigo(codigo);
        return ResponseEntity.ok(new LancamentoDTO(lancamento));
    }

    @PostMapping
    public ResponseEntity<LancamentoDTO> cadastrarLancamento(@RequestBody @Valid LancamentoRequestDTO lancamentoRequest, HttpServletResponse response){
        Lancamento lancamento = this.serviceLancamento.cadastrar(
               this.serviceLancamento.converterLancamentoRequestParaLancamentoEntity(lancamentoRequest));
        publisher.publishEvent(new RecursoCriadoEvent(this,response, lancamento.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(new LancamentoDTO(lancamento));
    }

}
