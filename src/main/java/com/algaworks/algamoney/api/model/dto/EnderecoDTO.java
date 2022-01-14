package com.algaworks.algamoney.api.model.dto;

import com.algaworks.algamoney.api.model.entity.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;

    public EnderecoDTO(Endereco endereco){
        logradouro = endereco.getLogradouro() == null ? "": endereco.getLogradouro();
        numero = endereco.getNumero() == null ? "": endereco.getNumero();
        complemento = endereco.getComplemento() == null ? "": endereco.getComplemento();
        bairro = endereco.getBairro() == null ? "": endereco.getBairro();
        cep = endereco.getCep() == null ? "": endereco.getCep();
        cidade = endereco.getCidade() == null ? "": endereco.getCidade();
        estado = endereco.getEstado() == null ? "": endereco.getEstado();
    }
}
