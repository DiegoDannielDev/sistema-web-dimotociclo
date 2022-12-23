package br.com.project.dimotocicloapi.adapters.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class UsuarioLogin {

  private String login;
  private String senha;
}
