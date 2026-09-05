package sptech.school.imobiliaria.exception;

public class RecursoNaoEncontradoException extends RuntimeException {

    private final Long id;

    public RecursoNaoEncontradoException(Long id) {
        super("Imóvel não encontrado: " + id);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
