package com.algaworks.algamoney.api.model.dto;

import com.algaworks.algamoney.api.model.entity.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    private Integer codigo;

    @NotNull(message = "Campo não pode ser nulo.")
    @NotBlank(message = "Campo não pode ser vazio.")
    @Size(min = 3, message = "Nome deve conter no mínimo 3 caracteres.")
    private String nome;

    @NotNull(message = "Campo não pode ser nulo.")
    private boolean ativo;
    private EnderecoDTO endereco;

    public PessoaDTO(Pessoa pessoa){
        codigo = pessoa.getCodigo();
        nome = pessoa.getNome();
        ativo = pessoa.isAtivo();
        if(pessoa.getEndereco() != null)
            endereco = new EnderecoDTO(pessoa.getEndereco());
    }
}
