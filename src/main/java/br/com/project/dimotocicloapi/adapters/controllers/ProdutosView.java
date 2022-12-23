package br.com.project.dimotocicloapi.adapters.controllers;

import br.com.project.dimotocicloapi.HelloApplication;
import br.com.project.dimotocicloapi.adapters.rest.impl.ProdutoRestImpl;
import br.com.project.dimotocicloapi.adapters.rest.request.ProdutoRequest;
import br.com.project.dimotocicloapi.adapters.rest.request.Validations;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.controlsfx.control.Notifications;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProdutosView implements Initializable {

  private final ProdutoRestImpl produtoRest = new ProdutoRestImpl();
  @FXML private static final Stage stage = new Stage();
  @FXML public ComboBox<String> tipoProduto;
  @FXML public TextField codigoBarrasEan;
  @FXML public TextField codigoBarrasInterno;
  @FXML public TextField descricaoProduto;
  @FXML public TextField referenciaProduto;
  @FXML public TextField valorVendaProduto;
  @FXML public TextField quantidadeEstoqueProduto;
  @FXML public TextField marcaProduto;
  @FXML public TextField fornecedor;
  @FXML public Button btnPesquisar;
  @FXML public Button btnDeletar;
  @FXML public Button btnNovo;
  @FXML public RadioButton rdAtivo;
  @FXML public RadioButton rdInativo;
  @FXML public Button btnUrlImagen;
  @FXML public ImageView imagemView;
  @FXML public TextField urlImagem;
  @FXML public TextField estoqueMin;
  @FXML public TextField estoqueMax;
  @FXML public TextField margenValorVendaVarejo;
  @FXML public TextField margemVendaAtacado;
  @FXML public TextField valorLucroVendaVarejo;
  @FXML public TextField valorLucroAtacado;
  @FXML public TextField valorVendaAtacado;
  @FXML public CheckBox controleEstoque;
  @FXML public ToggleGroup toggleGroup;
  public TextField valorCompra;
  public Button btnGerarCodigo;

  @FXML private DialogPane dialogPane;

  @SneakyThrows
  @FXML
  public void view() {
    FXMLLoader fxmlLoader =
        new FXMLLoader(HelloApplication.class.getResource("produtos-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setScene(scene);
    stage.setResizable(false);
    stage.setTitle("Cadastro de Produtos");

    stage.show();
  }

  @FXML
  public void carregarUrlArquivos() {
    FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showOpenDialog(stage);
    urlImagem.setText(file.getAbsolutePath());
    imagemView.setImage(new Image(file.getAbsolutePath()));
  }

  public void gerarCodigoBarras() {

    codigoBarrasEan.setText(String.valueOf(new Random().longs(13).findAny().getAsLong()));
    codigoBarrasInterno.setText(String.valueOf(new Random().nextInt(6)));
  }

  @FXML
  public void salvarProdutos() {
    validarCamposObrigatorios(
        List.of(
            Validations.builder().field(codigoBarrasEan.getText()).msg("Codigo de barras ").build(),
            Validations.builder()
                .field(descricaoProduto.getText())
                .msg("Descrição produto ")
                .build(),
            Validations.builder()
                .field(quantidadeEstoqueProduto.getText())
                .msg("Quantidade para estoque ")
                .build()));

    produtoRest.salvarProdutosApi(
        ProdutoRequest.builder()
            .codigoBarras(codigoBarrasEan.getText())
            .codigoBarrasInterno(codigoBarrasInterno.getText())
            //            .codigoFornecedor(Long.valueOf(fornecedor.getText()))
            .produtoMarca(marcaProduto.getText())
            .descricaoProduto(descricaoProduto.getText())
            .quantidadeEstoqueProduto(Long.valueOf(quantidadeEstoqueProduto.getText()))
            .referenciaProduto(referenciaProduto.getText())
            .valorVendaProduto(Double.valueOf(valorVendaProduto.getText()))
            .margemVendaAtacado(Double.valueOf(margemVendaAtacado.getText()))
            .margemVendaVarejo(Double.valueOf(margenValorVendaVarejo.getText()))
            .controleEstoque(controleEstoque.getText())
            .quantidadeMaximaEstoque(Long.valueOf(estoqueMax.getText()))
            .situacaoProduto(verificaSituacaoProduto())
            .quantidadeMinimaEstoque(Long.valueOf(estoqueMin.getText()))
            .unidadeMedida(tipoProduto.getValue())
            .urlImagen(urlImagem.getText())
            .categoria(null)
            .localizacao(null)
            .valorCompraProdutoUnitario(Double.valueOf(valorCompra.getText()))
            .build());
  }

  private void validarCamposObrigatorios(List<Validations> objectList) {
    List<Validations> list = new ArrayList<>();
    objectList.forEach(
        o -> {
          if (!o.getField().equals("") && o.getField() != null) {
            return;
          }
          list.add(o);
        });

    if (!list.isEmpty()) {
      ImageView icon = new ImageView();
      list.forEach(
          o ->
              Notifications.create()
                  .title("Campos Obrigatorios para salvar produto")
                  .owner(stage)
                  .hideAfter(Duration.seconds(3))
                  .position(Pos.TOP_RIGHT)
                  .graphic(icon)
                  .text(String.format("Campo não pode ser nullo %s", o.getMsg()))
                  .showWarning());
      throw new RuntimeException(
          objectList.stream().map(Validations::getMsg).collect(Collectors.joining(" ")));
    }
  }

  @Override
  @FXML
  public void initialize(URL url, ResourceBundle resourceBundle) {
    toggleGroup = new ToggleGroup();
    rdAtivo.setToggleGroup(toggleGroup);
    rdAtivo.setSelected(true);
    rdInativo.setToggleGroup(toggleGroup);
    validaFrancionamento();
  }

  private String verificaSituacaoProduto() {
    if (rdAtivo.isSelected()) {
      return "A";
    } else if (rdInativo.isSelected()) {
      return "I";
    }
    return null;
  }

  private void validaFrancionamento() {
    tipoProduto = new ComboBox<>();
    tipoProduto.setItems(FXCollections.observableArrayList(List.of("UN", "CX")));

    tipoProduto.valueProperty().addListener((observableValue, s, t1) -> observableValue.getValue());
  }
}
