# Imoboliária Helper — Front-end (React + Vite)

Gerado seguindo o padrão exigido pela disciplina de Front-end (`npm create vite@latest -- --template react`), consumindo a API REST desenvolvida em Programação Web (`GET /imoveis`, `POST /imoveis`).

## Como executar

Pré-requisito: Node.js 18+.

```bash
cd cliente
npm install
npm run dev
```

Abre em `http://localhost:5173`. **A API precisa estar rodando em `http://localhost:8080`** (o back-end já está configurado com `@CrossOrigin` liberando essa origem).

Para lint: `npm run lint` (oxlint, mesma ferramenta do scaffold do professor).

## Identidade visual

Tema: "livro de registro imobiliário" — papel envelhecido, tinta verde-tinteiro, selo de lacre vermelho-tijolo e cartões de imóvel em formato de etiqueta de vitrine (com furo no topo, como se fossem destacados de um talão). Tipografia: Fraunces (display), Inter (corpo), IBM Plex Mono (preços e dados).

## Estrutura (seguindo o padrão do professor)

```
cliente/
├── public/
│   └── favicon.svg
├── src/
│   ├── App.jsx / App.module.css      -> cabeçalho, abas, estado compartilhado
│   ├── index.css                     -> tokens de cor/tipografia globais
│   ├── main.jsx                      -> ponto de entrada (StrictMode + createRoot)
│   ├── servicos/
│   │   └── imovelService.js          -> chamadas GET/POST à API
│   └── componentes/                  -> pasta plana, sem subpasta por componente
│       ├── CadastroImovel.jsx / .module.css
│       ├── ListaImoveis.jsx / .module.css
│       └── ImovelCard.jsx / .module.css
├── index.html
├── package.json
├── vite.config.js
└── .oxlintrc.json
```

**Diferença deliberada do template padrão:** em `main.jsx`, o import de `./index.css` fica ativo (no scaffold em branco ele vem comentado), porque é lá que ficam as variáveis de cor/tipografia usadas por todos os `*.module.css` do projeto.

## Checklist dos requisitos da disciplina

- [x] Mínimo 5 campos de cadastro (são 7: logradouro, número, bairro, cidade, preço, área, tipo)
- [x] Tela/componente de cadastro (`CadastroImovel`)
- [x] Tela/componente de exibição (`ListaImoveis` + `ImovelCard`)
- [x] Consome a API com GET e POST (`servicos/imovelService.js`)
- [x] Componentização (`App`, `CadastroImovel`, `ListaImoveis`, `ImovelCard`)
- [x] Estado controla interação (`useState` nos campos do form, na aba ativa, na lista)
- [x] JSX na construção das interfaces
- [x] CSS Modules em todos os componentes (`*.module.css`)
- [x] Estados de carregamento, sucesso e erro tratados (no cadastro e na listagem)
- [x] Dados da tela de consulta vêm exclusivamente da API (sem dados estáticos/mockados)
