package br.com.project.dimotocicloapi.adapters.controllers;

import br.com.project.dimotocicloapi.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SystemInit {
  private static final Stage stage = new Stage();
  private final UsuarioView usuarioView = new UsuarioView();
  private final ProdutosView produtosView = new ProdutosView();
  @FXML private KeyEvent keyEvent;

  public void view() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("system-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setScene(scene);
    stage.setMaximized(true);
    stage.show();
  }

  @FXML
  protected void onClickUsuarios() throws IOException {
    usuarioView.view();
  }

  @FXML
  protected void onClickProdutos() {
    produtosView.view();
  }

  @FXML
  protected void onAction(KeyEvent event) {
    if (event.getCode() == KeyCode.P) {
      produtosView.view();
    }
  }
}
