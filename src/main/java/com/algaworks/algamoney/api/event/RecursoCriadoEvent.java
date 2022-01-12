package com.algaworks.algamoney.api.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

@Getter
public class RecursoCriadoEvent extends ApplicationEvent {

    private HttpServletResponse response;
    private Integer codigo;

    public RecursoCriadoEvent(Object source,HttpServletResponse response, Integer codigo) {
        super(source);
        this.response = response;
        this.codigo = codigo;
    }
}
