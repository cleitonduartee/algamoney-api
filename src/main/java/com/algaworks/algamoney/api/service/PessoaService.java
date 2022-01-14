package com.algaworks.algamoney.api.service;

import com.algaworks.algamoney.api.model.dto.EnderecoDTO;
import com.algaworks.algamoney.api.model.dto.PessoaDTO;
import com.algaworks.algamoney.api.model.entity.Endereco;
import com.algaworks.algamoney.api.model.entity.Pessoa;
import com.algaworks.algamoney.api.model.exception.RecursoNaoEncontrado;
import com.algaworks.algamoney.api.repository.PessoaRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService implements IPessoaService{

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public List<Pessoa> buscarTodos() {
        return pessoaRepository.findAll();
    }

    @Override
    public Pessoa buscarPorCodigo(Integer codigo) {

        return pessoaRepository.findById(codigo)
                .orElseThrow(()-> new RecursoNaoEncontrado("Não foi possível localizar pessoa de código: "+codigo+". Favor verificar código e tentar novamente."));
    }

    @Override
    public Pessoa cadastrar(Pessoa pessoa) {

        return pessoaRepository.save(pessoa);
    }

    @Override
    public void deletar(Integer codigo) {
        this.buscarPorCodigo(codigo);
        pessoaRepository.deleteById(codigo);
    }

    @Override
    public void atualizar(Pessoa entity) {

    }

    @Override
    public void atualizarEndereco(Pessoa pessoa) {

    }

    @Override
    public Pessoa convertePessoaDtoParaPessoaEntity(PessoaDTO pessoaDTO) {
        Pessoa pessoaEntity = new Pessoa();
        if(pessoaDTO.getEndereco() != null){
            pessoaEntity.setEndereco(new Endereco());
            BeanUtils.copyProperties(pessoaDTO.getEndereco(),pessoaEntity.getEndereco() );
        }

        BeanUtils.copyProperties(pessoaDTO,pessoaEntity );
        return pessoaEntity;
    }
}
