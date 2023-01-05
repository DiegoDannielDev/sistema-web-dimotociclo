package br.com.project.dimotocicloapi.adapters.resttemplate.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class UsuarioLoginRequest {

  private String login;
  private String senha;
}
