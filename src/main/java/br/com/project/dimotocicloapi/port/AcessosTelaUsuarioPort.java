package br.com.project.dimotocicloapi.port;

import br.com.project.dimotocicloapi.adapters.resttemplate.request.AcessoTelaPermissaoRequest;
import br.com.project.dimotocicloapi.adapters.resttemplate.response.AcessoTelaPermissaoResponse;

public interface AcessosTelaUsuarioPort {
  AcessoTelaPermissaoResponse validaAcessoUsuario(AcessoTelaPermissaoRequest permissaoRequest);
}
