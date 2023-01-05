package br.com.project.dimotocicloapi.adapters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Telas {
  USUARIOS("Usuarios"),
  PRODUTOS("Usuarios"),
  LISTA_USUARIOS("Lista usuarios"),
  FINANCEIRO("Financeiro"),
  LOGIN("Login");

  private String nomeTela;
}
