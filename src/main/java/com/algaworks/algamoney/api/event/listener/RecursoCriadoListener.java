package com.algaworks.algamoney.api.event.listener;

import com.algaworks.algamoney.api.event.RecursoCriadoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

    @Override
    public void onApplicationEvent(RecursoCriadoEvent event) {
        HttpServletResponse response = event.getResponse();
        Integer codigo = event.getCodigo();
        addHeaderLocation(response, codigo);
    }

    private void addHeaderLocation(HttpServletResponse response, Integer codigo) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(codigo).toUri();

        response.setHeader("Location", uri.toASCIIString());
    }
}
