# Projeto-Individual-3-SEM-Imobili-ria
Imoboliária Helper — Projeto Integrador

Projeto integrador entre as disciplinas de Programação Web (back-end) e Front-end, com o tema "Imobiliária". Permite cadastrar e consultar imóveis (logradouro, número, bairro, cidade, preço, área e tipo).

Este README dá o panorama geral. Para detalhes de execução de cada parte, veja api/README.md e cliente/README.md.

Estrutura do repositório
projeto-integrador/
├── api/            -> Back-end (Java + Spring Boot + JdbcTemplate + H2)
│   └── README.md   -> como rodar, endpoints, testes com curl
├── cliente/         -> Front-end (React + Vite)
│   └── README.md   -> como rodar, identidade visual, checklist de requisitos
├── contrato-api-imoboliaria-helper.md  -> contrato de integração (endpoints, formatos, status HTTP)
└── README.md        -> este arquivo
Como rodar o projeto completo

A ordem importa: o front-end depende da API já estar de pé.

1. Suba a API (deixe rodando em um terminal):

bash
cd api
./mvnw spring-boot:run

Sobe em http://localhost:8080. O banco H2 é criado automaticamente (script de criação da tabela em api/src/main/resources/schema.sql).

2. Suba o front-end (em outro terminal):

bash
cd cliente
npm install
npm run dev

Sobe em http://localhost:5173 e já consome a API automaticamente.

Como cliente e API se comunicam

O front-end faz requisições HTTP diretas à API (fetch, em cliente/src/servicos/imovelService.js):

Ação no front	Requisição	Resposta esperada
Abrir a aba "Imóveis registrados"	GET http://localhost:8080/imoveis	200 + lista de imóveis
Enviar o formulário "Novo registro"	POST http://localhost:8080/imoveis	201 + imóvel criado, ou 400 com os erros de validação

Como as duas aplicações rodam em portas diferentes (5173 e 8080), a API libera essa origem via @CrossOrigin no ImovelController. O contrato completo — campos, exemplos de request/response e códigos de status — está documentado em contrato-api-imoboliaria-helper.md.

Stack
Back-end: Java 21, Spring Boot 4.1.1, JdbcTemplate, banco H2 (arquivo local)
Front-end: React 19, Vite 8, CSS Modules