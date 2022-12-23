package br.com.project.dimotocicloapi.adapters.rest;

import br.com.project.dimotocicloapi.adapters.rest.request.UsuarioLogin;
import br.com.project.dimotocicloapi.adapters.rest.request.UsuarioResponse;

public interface UsuarioLoginPort {

  UsuarioResponse validarUsuario(UsuarioLogin usuarioLogin);
}
