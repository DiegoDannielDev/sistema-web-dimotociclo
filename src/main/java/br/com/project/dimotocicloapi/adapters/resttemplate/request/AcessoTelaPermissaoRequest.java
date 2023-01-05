package br.com.project.dimotocicloapi.adapters.resttemplate.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AcessoTelaPermissaoRequest {

  private Long codigoUsuario;

  private String nomeTela;
}
