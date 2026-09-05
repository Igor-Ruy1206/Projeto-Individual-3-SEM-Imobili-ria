const BASE_URL = 'http://localhost:8080'


async function lerErro(resposta) {
  try {
    const corpo = await resposta.json()
    if (corpo?.detalhes?.length) {
      return corpo.detalhes.join(' | ')
    }
    return corpo?.erro || `Erro ${resposta.status}`
  } catch {
    return `Erro ${resposta.status}`
  }
}

export async function listarImoveis() {
  const resposta = await fetch(`${BASE_URL}/imoveis`)
  if (!resposta.ok) {
    throw new Error(await lerErro(resposta))
  }
  return resposta.json()
}

export async function cadastrarImovel(imovel) {
  const resposta = await fetch(`${BASE_URL}/imoveis`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(imovel),
  })
  if (!resposta.ok) {
    throw new Error(await lerErro(resposta))
  }
  return resposta.json()
}
