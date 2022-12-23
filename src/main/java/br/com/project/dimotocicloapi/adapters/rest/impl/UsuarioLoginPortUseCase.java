package br.com.project.dimotocicloapi.adapters.rest.impl;

import br.com.project.dimotocicloapi.adapters.config.RestTemplateConfig;
import br.com.project.dimotocicloapi.adapters.rest.request.UsuarioResponse;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UsuarioLoginPortUseCase {

  private final RestTemplateConfig restTemplate = new RestTemplateConfig();

  @SneakyThrows
  public UsuarioResponse validarUsuario(String nome, String senha) {
    String urlTemplate =
        UriComponentsBuilder.fromUri(
                URI.create("http://localhost:8082/api/usuarios/buscar-usuario"))
            .queryParam("nome", nome)
            .queryParam("senha", senha)
            .encode()
            .toUriString();
    var json = restTemplate.getForEntity(urlTemplate, String.class).getBody();
    return new Gson().fromJson(json, UsuarioResponse.class);
  }

  @SneakyThrows
  public List<UsuarioResponse> buscarTodos() {
    String urlTemplate =
        UriComponentsBuilder.fromUri(
                URI.create("http://localhost:8082/api/usuarios/buscar_todos_usuario"))
            .encode()
            .toUriString();
    return Arrays.asList(
        Objects.requireNonNull(restTemplate.getForObject(urlTemplate, UsuarioResponse[].class)));
  }

  @SneakyThrows
  public UsuarioResponse salvarUsuario(UsuarioResponse usuarioResponse) {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);
    var entity = new HttpEntity<>(usuarioResponse, headers);
    String urlTemplate =
        UriComponentsBuilder.fromUri(
                URI.create("http://localhost:8082/api/usuarios/salvar_usuarios"))
            .encode()
            .toUriString();
    var json = restTemplate.postForEntity(urlTemplate, entity, String.class).getBody();
    return new Gson().fromJson(json, UsuarioResponse.class);
  }
}
