package com.algaworks.algamoney.api.model.dto;

import com.algaworks.algamoney.api.model.enuns.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoRequestDTO {

    private Integer codig;
    @NotBlank(message = "Campo não pode ser vazio ou nulo.")
    private String descricao;

    @NotNull(message = "Campo não pode ser nulo.")
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;

    @NotNull(message = "Campo não pode ser nulo.")
    private Double valor;
    private String observacao;

    @NotNull(message = "Campo não pode ser nulo.")
    private TipoLancamento tipo;

    @NotNull(message = "Campo não pode ser nulo.")
    private Integer codigoCategoria;

    @NotNull(message = "Campo não pode ser nulo.")
    private Integer codigoPessoa;
}
