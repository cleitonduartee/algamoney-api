package com.algaworks.algamoney.api.exceptionhandler;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;

@SuperBuilder
@Getter
public class StandartErrorMap extends StandartError{
    private Map<String, String> errors = new HashMap<>();
}
