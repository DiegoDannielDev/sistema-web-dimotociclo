package br.com.project.dimotocicloapi.adapters.controllers;

import br.com.project.dimotocicloapi.HelloApplication;
import br.com.project.dimotocicloapi.adapters.rest.impl.UsuarioLoginPortUseCase;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
public class LoginView {

  private static final Stage stage = new Stage();
  private final SystemInit systemInit = new SystemInit();
  @FXML public Hyperlink passwordIn;
  @FXML private TextField textField;
  @FXML private Label textErro;
  @FXML private PasswordField passwordField;
  @FXML private AnchorPane anchorPane;

  protected void setVisible() {
    textErro.setVisible(false);
  }

  @FXML
  protected void login() throws IOException {
    setVisible();
    UsuarioLoginPortUseCase usuarioLoginPortUseCase = new UsuarioLoginPortUseCase();
    var user = usuarioLoginPortUseCase.validarUsuario(textField.getText(), passwordField.getText());
    if (Objects.isNull(user)) {
      textErro.setVisible(true);
      textErro.setText("Usuario não encontrado");
    } else {
      systemInit.view();
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

  public void carregarForms() {
    Form.of(
            Group.of(
                Field.ofStringType("").label("Username"),
                Field.ofStringType("").label("Password")
                        .required("This field can’t be empty")))
        .title("Login");
  }
}
