package sptech.school.imobiliaria.dto;

import java.util.List;

/**
 * Formato padrão de erro devolvido pela API (400 e 404),
 * já no formato acordado no contrato com o front-end.
 */
public class ErroResposta {

    private String erro;
    private List<String> detalhes;

    public ErroResposta() {
    }

    public ErroResposta(String erro, List<String> detalhes) {
        this.erro = erro;
        this.detalhes = detalhes;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public List<String> getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(List<String> detalhes) {
        this.detalhes = detalhes;
    }
}
