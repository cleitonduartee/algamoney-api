package com.algaworks.algamoney.api.service;

import com.algaworks.algamoney.api.model.dto.CategoriaDTO;
import com.algaworks.algamoney.api.model.entity.Categoria;
import com.algaworks.algamoney.api.model.exception.RecursoNaoEncontrado;
import com.algaworks.algamoney.api.repository.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements ICategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria buscarPorCodigo(Integer codigo) {
        return categoriaRepository
                .findById(codigo)
                .orElseThrow(
                        ()->new RecursoNaoEncontrado("Não foi possível localizar categoria de código: "
                                +codigo
                                +". Favor verificar código e tentar novamente."));
    }

    @Override
    public Categoria cadastrar(Categoria entity) {
        return categoriaRepository.save(entity);
    }

    @Override
    public void deletar(Integer codigo) {
        this.buscarPorCodigo(codigo);
        categoriaRepository.deleteById(codigo);
    }

    @Override
    public Categoria atualizar(Integer codigo, CategoriaDTO categoriaDTOto) {
        Categoria categoriaSalva = this.buscarPorCodigo(codigo);
        BeanUtils.copyProperties(categoriaDTOto, categoriaSalva, "codigo");
        return categoriaRepository.save(categoriaSalva);
    }

    @Override
    public Categoria converteCategoriaDtoParaCategoriaEntity(CategoriaDTO categoriaDto) {
        Categoria categoria = new Categoria();
        BeanUtils.copyProperties(categoriaDto, categoria);
        return categoria;
    }
}
