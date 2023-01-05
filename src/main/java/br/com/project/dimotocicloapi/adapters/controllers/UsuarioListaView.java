package br.com.project.dimotocicloapi.adapters.controllers;

import br.com.project.dimotocicloapi.HelloApplication;
import br.com.project.dimotocicloapi.domain.model.UsuarioListaModel;
import br.com.project.dimotocicloapi.domain.useCase.UsuarioLoginUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class UsuarioListaView implements Initializable {
  private static final Stage stage = new Stage();
  public TableView<UsuarioListaModel> usuarioListTable;
  public TableColumn<UsuarioListaModel, Long> codigoUsuario;
  public TableColumn<UsuarioListaModel, String> nomeUsuario;
  public TableColumn<UsuarioListaModel, String> tipoUsuario;
  private final UsuarioLoginUseCase usuarioLoginPort = new UsuarioLoginUseCase();

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    codigoUsuario.setCellValueFactory(new PropertyValueFactory<>("codigoUsuario"));
    nomeUsuario.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));
    tipoUsuario.setCellValueFactory(new PropertyValueFactory<>("tipoUsuario"));

    usuarioListTable.setItems(carregarLista());
  }

  private ObservableList<UsuarioListaModel> carregarLista() {
//    usuarioLoginPort.createUsuarioLoginUseCase();
    var user = usuarioLoginPort.buscarTodos();
    var listaModels =
        user.stream()
            .map(
                userFor ->
                    new UsuarioListaModel(
                        String.valueOf(userFor.getCodigo()), userFor.getNome(), "Adm"))
            .collect(Collectors.toList());
    return FXCollections.observableArrayList(listaModels);
  }

  @SneakyThrows
  @FXML
  public void view() {
    FXMLLoader fxmlLoader =
        new FXMLLoader(HelloApplication.class.getResource("usuario-lista-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setScene(scene);
    stage.setResizable(false);
    stage.setTitle("Lista de Usuarios");

    stage.show();
  }
}
