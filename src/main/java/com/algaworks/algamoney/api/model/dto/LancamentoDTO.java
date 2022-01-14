package com.algaworks.algamoney.api.model.dto;

import com.algaworks.algamoney.api.model.enuns.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoDTO {

    private Integer codigo;
    private String descricao;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private Double valor;
    private String observacao;
    private TipoLancamento tipo;
    private CategoriaDTO categoria;
    private PessoaDTO pessoa;
}
