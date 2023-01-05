package br.com.project.dimotocicloapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UsuarioDTO {

  private Integer codigo;
  private String nome;
  private String login;
  private String senha;
  private String telefone;
  private String email;
  private String msgLogin;
}
