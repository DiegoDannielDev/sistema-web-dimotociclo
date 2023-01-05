package br.com.project.dimotocicloapi.domain.useCase;

import br.com.project.dimotocicloapi.adapters.resttemplate.impl.AcessosTelaUsuarioRestImpl;
import br.com.project.dimotocicloapi.adapters.resttemplate.request.AcessoTelaPermissaoRequest;
import javafx.scene.control.Alert;

public class ValidaAcessos {
  private final AcessosTelaUsuarioRestImpl acessosTelaUsuarioRestImpl =
      new AcessosTelaUsuarioRestImpl();

  public boolean validaAcesso(Long codigoUsuario, String nomeTela) {
    var userValid =
        acessosTelaUsuarioRestImpl.validaAcessoUsuario(
            AcessoTelaPermissaoRequest.builder()
                .codigoUsuario(codigoUsuario)
                .nomeTela(nomeTela)
                .build());

    if (userValid.getMessage().equalsIgnoreCase("true")) {
      return true;
    }
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Notificação de acesso a tela");
    alert.setHeaderText(
        String.format("Usuario não tem acesso a tela de %s", nomeTela.toLowerCase()));
    alert.show();
    return false;
  }
}
