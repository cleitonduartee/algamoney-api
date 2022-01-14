package com.algaworks.algamoney.api.service;

import com.algaworks.algamoney.api.model.dto.LancamentoDTO;
import com.algaworks.algamoney.api.model.entity.Lancamento;
import com.algaworks.algamoney.api.model.exception.RecursoNaoEncontrado;
import com.algaworks.algamoney.api.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoService implements ILancamentoService{

    private LancamentoRepository lancamentoRepository;

    @Autowired
    public LancamentoService(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    @Override
    public List<Lancamento> buscarTodos() {
        return lancamentoRepository.findAll();
    }

    @Override
    public Lancamento buscarPorCodigo(Integer codigo) {
        return lancamentoRepository.findById(codigo).orElseThrow(()-> new RecursoNaoEncontrado("Não foi possível localizar o lançamento de código: "+codigo+". Favor verificar código e tentar novamente."));
    }

    @Override
    public Lancamento cadastrar(Lancamento entity) {
        return null;
    }

    @Override
    public void deletar(Integer codigo) {

    }

    @Override
    public Lancamento atualizar(Integer codigo, LancamentoDTO dto) {
        return null;
    }
}
