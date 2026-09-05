package sptech.school.imobiliaria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sptech.school.imobiliaria.dto.ErroResposta;

import java.util.List;

/**
 * Centraliza a tradução de exceções em respostas HTTP coerentes,
 * garantindo que o servidor rejeite requisições inválidas mesmo
 * quando enviadas direto por Postman/Insomnia/curl (sem passar pelo front).
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> tratarNaoEncontrado(RecursoNaoEncontradoException ex) {
        ErroResposta corpo = new ErroResposta(
                "Imóvel não encontrado",
                List.of("id " + ex.getId() + " não existe")
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(corpo);
    }

    @ExceptionHandler(DadosInvalidosException.class)
    public ResponseEntity<ErroResposta> tratarDadosInvalidos(DadosInvalidosException ex) {
        ErroResposta corpo = new ErroResposta("Dados inválidos", ex.getErros());
        return ResponseEntity.badRequest().body(corpo);
    }

    /**
     * Captura JSON malformado ou valor de "tipo" fora do enum
     * (ex: {"tipo": "GALPAO"}), devolvendo 400 em vez de deixar
     * o Spring estourar um 500 genérico.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResposta> tratarJsonInvalido(HttpMessageNotReadableException ex) {
        ErroResposta corpo = new ErroResposta(
                "JSON inválido",
                List.of("Verifique o formato do corpo enviado e se 'tipo' é CASA, APARTAMENTO ou TERRENO")
        );
        return ResponseEntity.badRequest().body(corpo);
    }
}
