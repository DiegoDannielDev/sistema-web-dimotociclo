package br.com.project.dimotocicloapi.adapters.resttemplate.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UsuarioRequest implements Serializable {

  private Integer codigo;
  private String nome;
  private String login;
  private String senha;
  private String telefone;
  private String email;
}
