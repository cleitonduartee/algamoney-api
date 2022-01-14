package com.algaworks.algamoney.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

    private Integer codigo;

    @NotNull(message = "Campo não pode ser nulo.")
    @NotBlank(message = "Campo não pode ser vazio.")
    @Size(min = 3, max = 20, message = "Campo deve ter entre 3 a 20 caracteres.")
    private String nome;
}
