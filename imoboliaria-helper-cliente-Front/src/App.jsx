import { useState } from 'react'
import CadastroImovel from './componentes/CadastroImovel.jsx'
import ListaImoveis from './componentes/ListaImoveis.jsx'
import styles from './App.module.css'

function App() {
  const [abaAtiva, setAbaAtiva] = useState('lista')
  const [chaveAtualizacao, setChaveAtualizacao] = useState(0)

  function aoCadastrarComSucesso() {
    setChaveAtualizacao((atual) => atual + 1)
    setAbaAtiva('lista')
  }

  return (
    <div className={styles.pagina}>
      <header className={styles.cabecalho}>
        <span className={styles.selo}>IH</span>
        <div>
          <h1 className={styles.titulo}>Imoboliária Helper</h1>
          <p className={styles.subtitulo}>Livro de registro de imóveis</p>
        </div>
      </header>

      <nav className={styles.abas}>
        <button
          type="button"
          className={abaAtiva === 'lista' ? styles.abaAtiva : styles.aba}
          onClick={() => setAbaAtiva('lista')}
        >
          Imóveis registrados
        </button>
        <button
          type="button"
          className={abaAtiva === 'cadastro' ? styles.abaAtiva : styles.aba}
          onClick={() => setAbaAtiva('cadastro')}
        >
          Novo registro
        </button>
      </nav>

      <main className={styles.conteudo}>
        {abaAtiva === 'cadastro' ? (
          <CadastroImovel onCadastrado={aoCadastrarComSucesso} />
        ) : (
          <ListaImoveis chaveAtualizacao={chaveAtualizacao} />
        )}
      </main>
    </div>
  )
}

export default App
