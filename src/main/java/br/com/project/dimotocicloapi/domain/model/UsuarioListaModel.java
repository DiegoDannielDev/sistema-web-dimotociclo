package br.com.project.dimotocicloapi.domain.model;

import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;


public class UsuarioListaModel {

  private final SimpleStringProperty codigoUsuario;
  private final SimpleStringProperty nomeUsuario;
  private final SimpleStringProperty tipoUsuario;

  public UsuarioListaModel(String codigoUsuario, String nomeUsuario, String tipoUsuario) {
    this.codigoUsuario = new SimpleStringProperty(codigoUsuario);
    this.nomeUsuario = new SimpleStringProperty(nomeUsuario);
    this.tipoUsuario = new SimpleStringProperty(tipoUsuario);
  }

  public String getCodigoUsuario() {
    return codigoUsuario.get();
  }

  public SimpleStringProperty codigoUsuarioProperty() {
    return codigoUsuario;
  }

  public String getNomeUsuario() {
    return nomeUsuario.get();
  }

  public SimpleStringProperty nomeUsuarioProperty() {
    return nomeUsuario;
  }

  public String getTipoUsuario() {
    return tipoUsuario.get();
  }

  public SimpleStringProperty tipoUsuarioProperty() {
    return tipoUsuario;
  }

  public void setCodigoUsuario(String codigoUsuario) {
    this.codigoUsuario.set(codigoUsuario);
  }

  public void setNomeUsuario(String nomeUsuario) {
    this.nomeUsuario.set(nomeUsuario);
  }

  public void setTipoUsuario(String tipoUsuario) {
    this.tipoUsuario.set(tipoUsuario);
  }
}
