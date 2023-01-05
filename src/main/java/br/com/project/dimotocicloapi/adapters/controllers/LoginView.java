package br.com.project.dimotocicloapi.adapters.controllers;

import br.com.project.dimotocicloapi.HelloApplication;
import br.com.project.dimotocicloapi.domain.model.UsuarioDTO;
import br.com.project.dimotocicloapi.domain.useCase.UsuarioLoginUseCase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginView {

  private static final Stage stage = new Stage();
  private final SystemInit systemInit = SystemInit.abstractCreate();
  @FXML public Hyperlink passwordIn;
  @FXML private TextField textField;
  @FXML private Label textErro;
  @FXML private PasswordField passwordField;
  @FXML private AnchorPane anchorPane;
  private final UsuarioLoginUseCase usuarioLoginUseCase = new UsuarioLoginUseCase();

  protected void setVisible() {
    textErro.setVisible(false);
  }

  @FXML
  protected void login() throws IOException {
    setVisible();
    var user =
        usuarioLoginUseCase.validarUsuario(
            UsuarioDTO.builder().login(textField.getText()).senha(passwordField.getText()).build());
    if (Objects.isNull(user)) {
      textErro.setVisible(true);
      textErro.setText("Usuario não encontrado");
    } else if (Objects.nonNull(user.getMsgLogin())) {
      textErro.setVisible(true);
      textErro.setText(user.getMsgLogin());
    } else {
      SystemInit.abstractCreate()
          .enviaValores(String.valueOf(user.getCodigo()), user.getNome())
          .view();
      stage.close();
    }
  }

  @FXML
  protected String recuperarSenha() {
    return "https://google.com.br";
  }

  public void view() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setScene(scene);
    stage.show();
  }
}
