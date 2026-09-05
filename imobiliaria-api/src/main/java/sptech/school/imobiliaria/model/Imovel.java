package sptech.school.imobiliaria.model;

import java.math.BigDecimal;

/**
 * Representa o recurso "Imovel", conforme o contrato de API combinado com o front-end.
 * Usado tanto para receber o corpo do POST quanto para montar as respostas dos GETs.
 */
public class Imovel {

    private Long id;
    private String logradouro;
    private Integer numero;
    private String bairro;
    private String cidade;
    private BigDecimal preco;
    private Double areaM2;
    private TipoImovel tipo;

    public Imovel() {
    }

    public Imovel(Long id, String logradouro, Integer numero, String bairro, String cidade,
                  BigDecimal preco, Double areaM2, TipoImovel tipo) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.preco = preco;
        this.areaM2 = areaM2;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Double getAreaM2() {
        return areaM2;
    }

    public void setAreaM2(Double areaM2) {
        this.areaM2 = areaM2;
    }

    public TipoImovel getTipo() {
        return tipo;
    }

    public void setTipo(TipoImovel tipo) {
        this.tipo = tipo;
    }
}
