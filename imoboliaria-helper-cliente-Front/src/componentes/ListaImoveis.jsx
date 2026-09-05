import { useEffect, useState } from 'react'
import { listarImoveis } from '../servicos/imovelService.js'
import ImovelCard from './ImovelCard.jsx'
import styles from './ListaImoveis.module.css'

function ListaImoveis({ chaveAtualizacao }) {
  const [imoveis, setImoveis] = useState([])
  const [carregando, setCarregando] = useState(true)
  const [erro, setErro] = useState(null)

  useEffect(() => {
    let cancelado = false

    async function buscar() {
      setCarregando(true)
      setErro(null)
      try {
        const dados = await listarImoveis()
        if (!cancelado) setImoveis(dados)
      } catch (e) {
        if (!cancelado) setErro(e.message)
      } finally {
        if (!cancelado) setCarregando(false)
      }
    }

    buscar()
    return () => {
      cancelado = true
    }
  }, [chaveAtualizacao])

  if (carregando) {
    return <p className={styles.status}>Consultando o registro…</p>
  }

  if (erro) {
    return (
      <div className={styles.erro} role="alert">
        <span className={styles.carimboErro}>Falha</span>
        <p>Não foi possível consultar os imóveis: {erro}</p>
        <p className={styles.dica}>Confira se a API está rodando em http://localhost:8080.</p>
      </div>
    )
  }

  if (imoveis.length === 0) {
    return (
      <div className={styles.vazio}>
        <p>Nenhum imóvel registrado ainda.</p>
        <p className={styles.dica}>Use a aba "Novo registro" para cadastrar o primeiro.</p>
      </div>
    )
  }

  return (
    <div className={styles.grade}>
      {imoveis.map((imovel) => (
        <ImovelCard key={imovel.id} imovel={imovel} />
      ))}
    </div>
  )
}

export default ListaImoveis
