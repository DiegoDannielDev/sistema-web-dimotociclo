package br.com.project.dimotocicloapi.adapters.resttemplate.impl;

import br.com.project.dimotocicloapi.adapters.config.RestTemplateConfig;
import br.com.project.dimotocicloapi.adapters.resttemplate.request.UsuarioRequest;
import br.com.project.dimotocicloapi.port.UsuarioLoginPort;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UsuarioLoginRestImpl implements UsuarioLoginPort {

  private final RestTemplateConfig restTemplate = new RestTemplateConfig();

  @SneakyThrows
  @Cacheable(value = "usuarioresponse", key = "#usuarioCodigo")
  public UsuarioRequest validarUsuario(String nome, String senha) {
    try {
      String urlTemplate =
          UriComponentsBuilder.fromUri(
                  URI.create("http://localhost:8082/api/usuarios/buscar-usuario"))
              .queryParam("nome", nome)
              .queryParam("senha", senha)
              .encode()
              .toUriString();
      var json = restTemplate.getForEntity(urlTemplate, String.class).getBody();
      return new Gson().fromJson(json, UsuarioRequest.class);

    } catch (HttpClientErrorException | HttpServerErrorException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }

  @SneakyThrows
  public List<UsuarioRequest> buscarTodos() {
    String urlTemplate =
        UriComponentsBuilder.fromUri(
                URI.create("http://localhost:8082/api/usuarios/buscar_todos_usuario"))
            .encode()
            .toUriString();
    return Arrays.asList(
        Objects.requireNonNull(restTemplate.getForObject(urlTemplate, UsuarioRequest[].class)));
  }

  @SneakyThrows
  public UsuarioRequest salvarUsuario(UsuarioRequest usuarioRequest) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.set(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);
      var entity = new HttpEntity<>(usuarioRequest, headers);
      String urlTemplate =
          UriComponentsBuilder.fromUri(
                  URI.create("http://localhost:8082/api/usuarios/salvar_usuarios"))
              .encode()
              .toUriString();

      var json = restTemplate.postForEntity(urlTemplate, entity, String.class).getBody();
      return new Gson().fromJson(json, UsuarioRequest.class);
    } catch (HttpClientErrorException | HttpServerErrorException ex) {
      throw new RuntimeException(ex.getMessage());
    }
  }
}
