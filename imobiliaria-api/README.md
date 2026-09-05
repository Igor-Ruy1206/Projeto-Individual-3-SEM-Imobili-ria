# imobiliaria — API (Back-end)

Projeto gerado via **Spring Initializr** (`sptech.school:imobiliaria`, Spring Boot 4.1.1, Java 21), com o código do recurso `Imovel` adicionado por cima: model, repository (JdbcTemplate puro), service com validação, controller REST e tratamento global de erros.

## Como executar

```bash
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080`. O banco H2 é criado automaticamente em `./data/imobiliaria.mv.db` na primeira execução (tabela `imoveis` criada via `src/main/resources/schema.sql`).

Console do H2 (opcional): `http://localhost:8080/h2-console` — JDBC URL `jdbc:h2:file:./data/imobiliaria`, usuário `sa`, senha em branco.

## Endpoints

| Método | Endpoint | Sucesso | Erro |
|---|---|---|---|
| GET | `/imoveis` | 200 | — |
| GET | `/imoveis/{id}` | 200 | 404 |
| POST | `/imoveis` | 201 | 400 |

Contrato completo (campos, exemplos, regras de validação) está em `contrato-api-imoboliaria-helper.md`, na raiz do repositório do projeto integrador.

## O que foi adicionado ao projeto gerado pelo Initializr

- **`pom.xml`**: adicionadas as dependências `spring-boot-starter-web`, `spring-boot-starter-jdbc` e `h2` (o Initializr, do jeito que você gerou, veio só com `spring-boot-starter` + `spring-boot-starter-test`).
- **`src/main/resources/schema.sql`** e **`script.sql`** (raiz): criação da tabela `imoveis`.
- **`src/main/resources/application.properties`**: mantida a linha original (`spring.application.name=imobiliaria`) e adicionadas as configs de datasource H2.
- **Pacote `sptech.school.imobiliaria`**: subpacotes `model`, `repository`, `service`, `controller`, `exception`, `dto` com o CRUD de `Imovel` (GET all, GET por id, POST).
- **`ImobiliariaApplication.java`** e a classe de teste: mantidos exatamente como o Initializr gerou.

## Testando com curl

```bash
curl http://localhost:8080/imoveis

curl -X POST http://localhost:8080/imoveis \
  -H "Content-Type: application/json" \
  -d '{
    "logradouro": "Rua das Palmeiras",
    "numero": 250,
    "bairro": "Jardim Europa",
    "cidade": "São Paulo",
    "preco": 450000.00,
    "areaM2": 78.5,
    "tipo": "APARTAMENTO"
  }'
```
