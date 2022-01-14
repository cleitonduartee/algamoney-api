package com.algaworks.algamoney.api.service;

import java.util.List;

public interface ICrudService<T> {

    public List<T> buscarTodos();
    public T buscarPorCodigo(Integer codigo);
    public T cadastrar(T entity);
    public void deletar(Integer codigo);
    public void atualizar(T entity);
}
