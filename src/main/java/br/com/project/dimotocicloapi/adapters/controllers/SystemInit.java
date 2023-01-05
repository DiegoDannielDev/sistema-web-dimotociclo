package br.com.project.dimotocicloapi.adapters.controllers;

import br.com.project.dimotocicloapi.HelloApplication;
import br.com.project.dimotocicloapi.adapters.Telas;
import br.com.project.dimotocicloapi.domain.useCase.ValidaAcessos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SystemInit {
  private static final Stage stage = new Stage();

  @FXML private static Label nomeUsuario;
  @FXML private static Label idUsuario;
  private UsuarioView usuarioView = new UsuarioView();
  private final ProdutosView produtosView = new ProdutosView();
  @FXML private KeyEvent keyEvent;
  public static final SystemInit systemInit = new SystemInit();
  private ValidaAcessos validaAcesso = new ValidaAcessos();
  private UsuarioListaView usuarioListaView = new UsuarioListaView();

  public static SystemInit abstractCreate() {
    nomeUsuario = new Label();
    idUsuario = new Label();
    return systemInit;
  }

  protected SystemInit enviaValores(String idUsuario, String nomeUsuario) {
    SystemInit.idUsuario.setVisible(true);
    SystemInit.nomeUsuario.setVisible(true);
    SystemInit.idUsuario.setText(idUsuario);
    SystemInit.nomeUsuario.setText(nomeUsuario);
    return this;
  }

  public void view() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("system-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setScene(scene);
    stage.setMaximized(true);
    stage.show();
  }

  @FXML
  protected void onClickUsuarios() {
    if (validaAcesso.validaAcesso(Long.valueOf(idUsuario.getText()), Telas.USUARIOS.name())) {
      usuarioView.view();
    }
  }

  @FXML
  protected void onClickProdutos() {
    if (validaAcesso.validaAcesso(Long.valueOf(idUsuario.getText()), Telas.PRODUTOS.name())) {
      produtosView.view();
    }
  }

  @FXML
  protected void onClickListaUsuarios() {
    if (validaAcesso.validaAcesso(Long.valueOf(idUsuario.getText()), Telas.LISTA_USUARIOS.name())) {
      usuarioListaView.view();
    }
  }

  @FXML
  protected void onAction(KeyEvent event) {
    if (event.getCode() == KeyCode.P) {
      produtosView.view();
    }
  }

  protected UsuarioView createUsuarioView() {
    return this.usuarioView = new UsuarioView();
  }
}
