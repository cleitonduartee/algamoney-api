package com.algaworks.algamoney.api.model.exception;

public class LancamentoNaoPermitido extends RuntimeException{

    public LancamentoNaoPermitido(String message) {
        super(message);
    }
}
