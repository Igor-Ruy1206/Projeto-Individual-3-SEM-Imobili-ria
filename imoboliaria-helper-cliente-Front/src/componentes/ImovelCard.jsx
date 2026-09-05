import styles from './ImovelCard.module.css'

const RESTULOS_TIPO = {
  CASA: 'Casa',
  APARTAMENTO: 'Apartamento',
  TERRENO: 'Terreno',
}

function formatarPreco(preco) {
  return new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL',
    maximumFractionDigits: 0,
  }).format(preco)
}

function ImovelCard({ imovel }) {
  return (
    <article className={styles.etiqueta}>
      <div className={styles.furo} aria-hidden="true" />
      <span className={styles.tipo}>{RESTULOS_TIPO[imovel.tipo] ?? imovel.tipo}</span>
      <p className={styles.preco}>{formatarPreco(imovel.preco)}</p>
      <p className={styles.endereco}>
        {imovel.logradouro}, {imovel.numero}
      </p>
      <p className={styles.localidade}>
        {imovel.bairro} · {imovel.cidade}
      </p>
      {imovel.areaM2 != null && <p className={styles.area}>{imovel.areaM2} m²</p>}
    </article>
  )
}

export default ImovelCard
