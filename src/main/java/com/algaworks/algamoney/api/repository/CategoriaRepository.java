package com.algaworks.algamoney.api.repository;

import com.algaworks.algamoney.api.model.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
