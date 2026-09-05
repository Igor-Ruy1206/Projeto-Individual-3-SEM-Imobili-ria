package sptech.school.imobiliaria.exception;

import java.util.List;

public class DadosInvalidosException extends RuntimeException {

    private final List<String> erros;

    public DadosInvalidosException(List<String> erros) {
        super("Dados inválidos: " + erros);
        this.erros = erros;
    }

    public List<String> getErros() {
        return erros;
    }
}
