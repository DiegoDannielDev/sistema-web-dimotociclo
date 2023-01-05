package br.com.project.dimotocicloapi.adapters.resttemplate.response;

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
public class ProdutoId {

  private Long codigoProduto;
  private String situacaoProduto;
  private String descricaoProduto;
  public String referenciaProduto;
  private String produtoMarca;
  private String urlImagen;
  private String categoria;
}
