package br.com.project.dimotocicloapi.adapters.resttemplate.impl;

import br.com.project.dimotocicloapi.adapters.config.RestTemplateConfig;
import br.com.project.dimotocicloapi.adapters.resttemplate.request.AcessoTelaPermissaoRequest;
import br.com.project.dimotocicloapi.adapters.resttemplate.response.AcessoTelaPermissaoResponse;
import br.com.project.dimotocicloapi.port.AcessosTelaUsuarioPort;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class AcessosTelaUsuarioRestImpl implements AcessosTelaUsuarioPort {

  private final RestTemplateConfig restTemplate = new RestTemplateConfig();

  @SneakyThrows
  @Override
  public AcessoTelaPermissaoResponse validaAcessoUsuario(
      AcessoTelaPermissaoRequest permissaoRequest) {
    String urlTemplate =
        UriComponentsBuilder.fromUri(URI.create("http://localhost:8082/api/acessos-tela/permissao"))
            .queryParam("nomeTela", permissaoRequest.getNomeTela())
            .queryParam("codigoUsuario", permissaoRequest.getCodigoUsuario())
            .encode()
            .toUriString();
    var json = restTemplate.getForEntity(urlTemplate, String.class).getBody();
    return new Gson().fromJson(json, AcessoTelaPermissaoResponse.class);
  }
}
