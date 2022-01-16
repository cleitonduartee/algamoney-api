package com.algaworks.algamoney.api.service;

import com.algaworks.algamoney.api.model.dto.LancamentoDTO;
import com.algaworks.algamoney.api.model.dto.LancamentoRequestDTO;
import com.algaworks.algamoney.api.model.entity.Categoria;
import com.algaworks.algamoney.api.model.entity.Lancamento;
import com.algaworks.algamoney.api.model.entity.Pessoa;
import com.algaworks.algamoney.api.model.exception.LancamentoNaoPermitido;
import com.algaworks.algamoney.api.model.exception.RecursoNaoEncontrado;
import com.algaworks.algamoney.api.repository.LancamentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoService implements ILancamentoService{

    private LancamentoRepository lancamentoRepository;
    private IPessoaService servicePessoa;
    private ICategoriaService serviceCategoria;

    @Autowired
    public LancamentoService(LancamentoRepository lancamentoRepository, IPessoaService servicePessoa,
                             ICategoriaService serviceCategoria) {
        this.lancamentoRepository = lancamentoRepository;
        this.servicePessoa = servicePessoa;
        this.serviceCategoria = serviceCategoria;
    }

    @Override
    public List<Lancamento> buscarTodos() {
        return lancamentoRepository.findAll();
    }

    @Override
    public Lancamento buscarPorCodigo(Integer codigo) {
        return lancamentoRepository.findById(codigo).orElseThrow(()-> new RecursoNaoEncontrado("Não foi possível localizar o lançamento de código: "+codigo+". Favor verificar código e tentar novamente."));
    }

    @Override
    public Lancamento cadastrar(Lancamento lancamento) {
       Pessoa pessoa = lancamento.getPessoa();
       if(!pessoa.isAtivo())
          throw new LancamentoNaoPermitido("Pessoa de id: "+pessoa.getCodigo()+" está inativa.");

       return this.lancamentoRepository.save(lancamento) ;
    }

    @Override
    public void deletar(Integer codigo) {

    }

    @Override
    public Lancamento atualizar(Integer codigo, LancamentoDTO dto) {
        return null;
    }

    @Override
    public Lancamento converterLancamentoRequestParaLancamentoEntity(LancamentoRequestDTO lancamentoRequest) {
        Categoria categoria = this.serviceCategoria.buscarPorCodigo(lancamentoRequest.getCodigoCategoria());
        Pessoa pessoa = this.servicePessoa.buscarPorCodigo(lancamentoRequest.getCodigoPessoa());

        Lancamento lancamento = new Lancamento();
        BeanUtils.copyProperties(lancamentoRequest, lancamento, "codigo","categoria","pessoa");
        lancamento.setCategoria(categoria);
        lancamento.setPessoa(pessoa);

        return lancamento;
    }
}
