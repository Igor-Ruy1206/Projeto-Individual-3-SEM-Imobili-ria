package sptech.school.imobiliaria.model;

/**
 * Tipos de imóvel aceitos pela API.
 * Mantido como enum para que o Jackson já rejeite (400) qualquer
 * valor de "tipo" fora dessa lista, antes mesmo de chegar na validação manual.
 */
public enum TipoImovel {
    CASA,
    APARTAMENTO,
    TERRENO
}
