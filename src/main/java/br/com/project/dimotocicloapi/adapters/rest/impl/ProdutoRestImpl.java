package br.com.project.dimotocicloapi.adapters.rest.impl;

import br.com.project.dimotocicloapi.adapters.config.RestTemplateConfig;
import br.com.project.dimotocicloapi.adapters.rest.request.ProdutoRequest;
import br.com.project.dimotocicloapi.adapters.rest.response.ProdutoResponse;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class ProdutoRestImpl {
  private final RestTemplateConfig restTemplate = new RestTemplateConfig();

  @SneakyThrows
  public ProdutoResponse salvarProdutosApi(ProdutoRequest usuarioResponse) {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);
    var entity = new HttpEntity<>(usuarioResponse, headers);
    String urlTemplate =
        UriComponentsBuilder.fromUri(
                URI.create("http://localhost:8082/api/produtos/salvar-produtos"))
            .encode()
            .toUriString();
    var json = restTemplate.postForEntity(urlTemplate, entity, String.class).getBody();
    return new Gson().fromJson(json, ProdutoResponse.class);
  }
}
