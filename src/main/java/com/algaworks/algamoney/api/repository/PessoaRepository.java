package com.algaworks.algamoney.api.repository;

import com.algaworks.algamoney.api.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
