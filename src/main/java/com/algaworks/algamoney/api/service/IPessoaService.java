package com.algaworks.algamoney.api.service;

import com.algaworks.algamoney.api.model.dto.PessoaDTO;
import com.algaworks.algamoney.api.model.entity.Pessoa;

public interface IPessoaService extends ICrudService<Pessoa, PessoaDTO>{

    public void atualizarEndereco(Pessoa pessoa);
    Pessoa convertePessoaDtoParaPessoaEntity(PessoaDTO pessoaDTO);
}
