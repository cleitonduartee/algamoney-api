package com.algaworks.algamoney.api.model.enuns;

public enum TipoLancamento {
    RECEITA(1,"Receita"),
    DESPESA(2,"Despesa");

    private int cod;
    private String value;

    TipoLancamento(int cod, String value) {
        this.cod = cod;
        this.value = value;
    }
}
