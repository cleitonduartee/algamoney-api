package com.algaworks.algamoney.api.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @NotNull(message = "Campo não pode ser nulo.")
    @NotBlank(message = "Campo não pode ser vazio.")
    @Size(min = 3, max = 20, message = "Campo deve ter entre 3 a 20 caracteres.")
    private String nome;

}
