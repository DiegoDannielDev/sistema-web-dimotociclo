package br.com.project.dimotocicloapi.adapters.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProdutoResponse {

    @JsonProperty("produto")
    private ProdutoId produtoId;

    @JsonProperty("produto_codigo_barras")
    private ProdutoCodigoBarrasResponse codigoBarras;

    @JsonProperty("produto_estoque")
    private EstoqueProdutoResponse estoqueProduto;

    @JsonProperty("produto_valores")
    private ValoresProdutosResponse valoresProdutos;

    @JsonProperty("produto_fornecedor")
    private FornecedorProdutosResponse fornecedorProdutosResponse;
}
