package br.com.project.dimotocicloapi.port;

import br.com.project.dimotocicloapi.adapters.resttemplate.request.ProdutoRequest;
import br.com.project.dimotocicloapi.adapters.resttemplate.response.ProdutoResponse;

public interface ProdutoPort {

  ProdutoResponse salvarProdutosApi(ProdutoRequest usuarioResponse);
}
