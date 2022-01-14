package com.algaworks.algamoney.api.service;

import java.util.List;

public interface ICrudService<T, K> {

    public List<T> buscarTodos();
    public T buscarPorCodigo(Integer codigo);
    public T cadastrar(T entity);
    public void deletar(Integer codigo);
    public T atualizar(Integer codigo, K dto);
}
