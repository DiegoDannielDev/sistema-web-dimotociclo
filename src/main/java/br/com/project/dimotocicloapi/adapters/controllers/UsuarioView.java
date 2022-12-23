package br.com.project.dimotocicloapi.adapters.controllers;

import br.com.project.dimotocicloapi.HelloApplication;
import br.com.project.dimotocicloapi.adapters.rest.impl.UsuarioLoginPortUseCase;
import br.com.project.dimotocicloapi.adapters.rest.request.UsuarioResponse;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UsuarioView implements Initializable {
  public TableView<String> tabelaUsuario;
  public TableColumn<String, SimpleStringProperty> columnNome;
  public TableColumn<String, SimpleStringProperty> columnLogin;
  public Label sucess;
  @FXML private TextField id;
  @FXML private TextField nome;
  @FXML private TextField login;
  @FXML private TextField telefone;
  @FXML private TextField email;
  @FXML private PasswordField password;
  @FXML private static final Stage stage = new Stage();
  private final UsuarioLoginPortUseCase usuarioLoginPortUseCase = new UsuarioLoginPortUseCase();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    preencherListaUsuarios();
  }

  @FXML
  public void preencherListaUsuarios() {

    var usuarios = usuarioLoginPortUseCase.buscarTodos();

    usuarios.forEach(
        usuarioResponse -> {
          ObservableList<String> itens = FXCollections.observableArrayList();
          itens.add(usuarioResponse.getNome());
          itens.add(usuarioResponse.getLogin());
          tabelaUsuario.setItems(itens);
        });
  }

  public UsuarioResponse salvarUsuario() {
    var user =
        UsuarioResponse.builder()
            .email(email.getText())
            .login(login.getText())
            .nome(nome.getText())
            .telefone(telefone.getText())
            .senha(password.getText())
            .build();
    var obj = this.usuarioLoginPortUseCase.salvarUsuario(user);
    if (Objects.nonNull(obj)) {
      sucess.setText("Usuario cadastrado com sucesso");
      preencherListaUsuarios();
      return obj;
    }
    return UsuarioResponse.builder().build();
  }

  public void view() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("usuario-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setScene(scene);
    stage.setResizable(false);
    stage.setTitle("Cadastro e configuração de Usuarios");
    stage.show();
  }
}
