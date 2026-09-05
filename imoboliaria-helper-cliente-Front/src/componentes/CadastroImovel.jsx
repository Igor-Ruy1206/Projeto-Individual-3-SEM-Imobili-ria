import { useState } from 'react'
import { cadastrarImovel } from '../servicos/imovelService.js'
import styles from './CadastroImovel.module.css'

const VALORES_INICIAIS = {
  logradouro: '',
  numero: '',
  bairro: '',
  cidade: '',
  preco: '',
  areaM2: '',
  tipo: 'CASA',
}

function CadastroImovel({ onCadastrado }) {
  const [valores, setValores] = useState(VALORES_INICIAIS)
  const [enviando, setEnviando] = useState(false)
  const [erro, setErro] = useState(null)

  function atualizarCampo(campo) {
    return (e) => {
      setValores((atual) => ({ ...atual, [campo]: e.target.value }))
    }
  }

  async function aoEnviar(e) {
    e.preventDefault()
    setErro(null)
    setEnviando(true)

    try {
      await cadastrarImovel({
        logradouro: valores.logradouro,
        numero: Number(valores.numero),
        bairro: valores.bairro,
        cidade: valores.cidade,
        preco: Number(valores.preco),
        areaM2: valores.areaM2 === '' ? null : Number(valores.areaM2),
        tipo: valores.tipo,
      })
      setValores(VALORES_INICIAIS)
      onCadastrado?.()
    } catch (e2) {
      setErro(e2.message)
    } finally {
      setEnviando(false)
    }
  }

  return (
    <form className={styles.ficha} onSubmit={aoEnviar}>
      <p className={styles.rotuloFicha}>Ficha de registro</p>

      <div className={styles.campo}>
        <label htmlFor="logradouro">Logradouro</label>
        <input
          id="logradouro"
          type="text"
          placeholder="Rua das Palmeiras"
          value={valores.logradouro}
          onChange={atualizarCampo('logradouro')}
          required
        />
      </div>

      <div className={styles.linhaDupla}>
        <div className={styles.campo}>
          <label htmlFor="numero">Número</label>
          <input
            id="numero"
            type="number"
            min="1"
            placeholder="250"
            value={valores.numero}
            onChange={atualizarCampo('numero')}
            required
          />
        </div>
        <div className={styles.campo}>
          <label htmlFor="tipo">Tipo</label>
          <select id="tipo" value={valores.tipo} onChange={atualizarCampo('tipo')}>
            <option value="CASA">Casa</option>
            <option value="APARTAMENTO">Apartamento</option>
            <option value="TERRENO">Terreno</option>
          </select>
        </div>
      </div>

      <div className={styles.linhaDupla}>
        <div className={styles.campo}>
          <label htmlFor="bairro">Bairro</label>
          <input
            id="bairro"
            type="text"
            placeholder="Jardim Europa"
            value={valores.bairro}
            onChange={atualizarCampo('bairro')}
            required
          />
        </div>
        <div className={styles.campo}>
          <label htmlFor="cidade">Cidade</label>
          <input
            id="cidade"
            type="text"
            placeholder="São Paulo"
            value={valores.cidade}
            onChange={atualizarCampo('cidade')}
            required
          />
        </div>
      </div>

      <div className={styles.linhaDupla}>
        <div className={styles.campo}>
          <label htmlFor="preco">Preço (R$)</label>
          <input
            id="preco"
            type="number"
            min="0.01"
            step="0.01"
            placeholder="450000.00"
            value={valores.preco}
            onChange={atualizarCampo('preco')}
            required
          />
        </div>
        <div className={styles.campo}>
          <label htmlFor="areaM2">Área (m², opcional)</label>
          <input
            id="areaM2"
            type="number"
            min="0.1"
            step="0.1"
            placeholder="78.5"
            value={valores.areaM2}
            onChange={atualizarCampo('areaM2')}
          />
        </div>
      </div>

      {erro && (
        <p className={styles.banda} role="alert">
          Não foi possível registrar: {erro}
        </p>
      )}

      <button type="submit" className={styles.botaoSelo} disabled={enviando}>
        {enviando ? 'Registrando…' : 'Carimbar registro'}
      </button>
    </form>
  )
}

export default CadastroImovel
