package sptech.school.imobiliaria.service;

import org.springframework.stereotype.Service;
import sptech.school.imobiliaria.exception.DadosInvalidosException;
import sptech.school.imobiliaria.exception.RecursoNaoEncontradoException;
import sptech.school.imobiliaria.model.Imovel;
import sptech.school.imobiliaria.repository.ImovelRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Regras de negócio do recurso Imovel: validação antes de persistir
 * e tradução de "não encontrado" para exceção específica.
 */
@Service
public class ImovelService {

    private final ImovelRepository repository;

    public ImovelService(ImovelRepository repository) {
        this.repository = repository;
    }

    public List<Imovel> listarTodos() {
        return repository.buscarTodos();
    }

    public Imovel buscarPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(id));
    }

    public Imovel cadastrar(Imovel imovel) {
        validar(imovel);
        return repository.salvar(imovel);
    }

    public void deletar(Long id) {
        boolean existia = repository.deletarPorId(id);
        if (!existia) {
            throw new RecursoNaoEncontradoException(id);
        }
    }

    private void validar(Imovel imovel) {
        List<String> erros = new ArrayList<>();

        if (isVazio(imovel.getLogradouro())) {
            erros.add("logradouro é obrigatório");
        }
        if (imovel.getNumero() == null || imovel.getNumero() <= 0) {
            erros.add("numero deve ser maior que zero");
        }
        if (isVazio(imovel.getBairro())) {
            erros.add("bairro é obrigatório");
        }
        if (isVazio(imovel.getCidade())) {
            erros.add("cidade é obrigatório");
        }
        if (imovel.getPreco() == null || imovel.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            erros.add("preco deve ser maior que zero");
        }
        if (imovel.getAreaM2() != null && imovel.getAreaM2() <= 0) {
            erros.add("areaM2 deve ser maior que zero quando informado");
        }
        if (imovel.getTipo() == null) {
            erros.add("tipo deve ser CASA, APARTAMENTO ou TERRENO");
        }

        if (!erros.isEmpty()) {
            throw new DadosInvalidosException(erros);
        }
    }

    private boolean isVazio(String valor) {
        return valor == null || valor.isBlank();
    }
}
