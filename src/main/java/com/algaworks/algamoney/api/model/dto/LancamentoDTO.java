package com.algaworks.algamoney.api.model.dto;

import com.algaworks.algamoney.api.model.entity.Lancamento;
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

    public LancamentoDTO(Lancamento lancamento) {
        codigo = lancamento.getCodigo();
        descricao = lancamento.getDescricao();
        dataVencimento = lancamento.getDataVencimento();
        dataPagamento = lancamento.getDataPagamento();
        valor = lancamento.getValor();
        observacao = lancamento.getObservacao();
        tipo = lancamento.getTipo();
        categoria = new CategoriaDTO(lancamento.getCategoria());
        pessoa = new PessoaDTO(lancamento.getPessoa());
    }
}
