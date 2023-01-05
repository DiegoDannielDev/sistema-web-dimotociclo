package br.com.project.dimotocicloapi.adapters.controllers;

import br.com.project.dimotocicloapi.HelloApplication;
import br.com.project.dimotocicloapi.adapters.resttemplate.request.Validations;
import br.com.project.dimotocicloapi.domain.model.UsuarioDTO;
import br.com.project.dimotocicloapi.domain.useCase.UsuarioLoginUseCase;
import br.com.project.dimotocicloapi.domain.useCase.ValidaAcessos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.SneakyThrows;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class UsuarioView implements Initializable {
  public Label sucess;
  @FXML private TextField id;
  @FXML private TextField nome;
  @FXML private TextField login;
  @FXML private TextField telefone;
  @FXML private TextField email;
  @FXML private PasswordField password;
  @FXML private static final Stage stage = new Stage();
  private final UsuarioLoginUseCase usuarioLoginRestImpl = new UsuarioLoginUseCase();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {}

  @FXML
  public void preencherListaUsuarios() {

    var usuarios = usuarioLoginRestImpl.buscarTodos();

    usuarios.forEach(
        usuarioRequest -> {
          ObservableList<String> itens = FXCollections.observableArrayList();
          itens.add(usuarioRequest.getNome());
          itens.add(usuarioRequest.getLogin());
        });
  }

  public UsuarioDTO salvarUsuario() {
    var user =
        UsuarioDTO.builder()
            .email(email.getText())
            .login(login.getText())
            .nome(nome.getText())
            .telefone(telefone.getText())
            .senha(password.getText())
            .build();
    try{
      var obj = this.usuarioLoginRestImpl.salvarUsuario(user);
      if (Objects.nonNull(obj)) {
        sucess.setText("Usuario cadastrado com sucesso");
        preencherListaUsuarios();
        return obj;
      }

    }catch (RuntimeException e){
      ImageView icon = new ImageView();
      Notifications.create()
              .title("Erro")
              .owner(stage)
              .hideAfter(Duration.seconds(5))
              .position(Pos.TOP_CENTER)
              .graphic(icon)
              .text(e.getMessage())
              .showWarning();
      throw new RuntimeException(e);
    }
    return UsuarioDTO.builder().build();
  }

  @SneakyThrows
  public void view() {
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("usuario-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setScene(scene);
    stage.setResizable(false);
    stage.setTitle("Cadastro e configuração de Usuarios");
    stage.show();
  }
}
