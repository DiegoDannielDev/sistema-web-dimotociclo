package br.com.project.dimotocicloapi.adapters.resttemplate.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProdutoRequest {

  private Long codigoProduto;

  private String codigoBarras;

  private String codigoBarrasInterno;

  private String descricaoProduto;

  private String unidadeMedida;

  private Long quantidadeEstoqueProduto;

  private Long quantidadeMaximaEstoque;

  private Long quantidadeMinimaEstoque;

  private String controleEstoque;

  public String referenciaProduto;

  private String categoria;

  public Double margemVendaAtacado;

  public Double margemVendaVarejo;

  private Double valorVendaProduto;

  private Double valorTotalCompra;

  private Double valorCompraProdutoUnitario;

  private Long codigoFornecedor;

  private String produtoMarca;

  private String localizacao;

  private String urlImagen;

  private String situacaoProduto;
}
