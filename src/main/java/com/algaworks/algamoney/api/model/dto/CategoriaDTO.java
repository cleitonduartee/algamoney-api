package com.algaworks.algamoney.api.model.dto;

import com.algaworks.algamoney.api.model.entity.Categoria;
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

    @NotBlank(message = "Campo não pode ser vazio ou nulo.")
    @Size(min = 3, max = 20, message = "Campo deve ter entre 3 a 20 caracteres.")
    private String nome;

    public CategoriaDTO(Categoria categoria) {
        codigo = categoria.getCodigo();
        nome = categoria.getNome();
    }
}
