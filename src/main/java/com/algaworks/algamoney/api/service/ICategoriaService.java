package com.algaworks.algamoney.api.service;

import com.algaworks.algamoney.api.model.dto.CategoriaDTO;
import com.algaworks.algamoney.api.model.dto.PessoaDTO;
import com.algaworks.algamoney.api.model.entity.Categoria;
import com.algaworks.algamoney.api.model.entity.Pessoa;

public interface ICategoriaService extends ICrudService<Categoria, CategoriaDTO>{

    Categoria converteCategoriaDtoParaCategoriaEntity(CategoriaDTO categoriaDto);
}
