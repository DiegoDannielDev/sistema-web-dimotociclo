package br.com.project.dimotocicloapi.port;

import br.com.project.dimotocicloapi.adapters.resttemplate.request.UsuarioRequest;

import java.util.List;

public interface UsuarioLoginPort {

  UsuarioRequest validarUsuario(String nome, String senha);

  List<UsuarioRequest> buscarTodos();

  UsuarioRequest salvarUsuario(UsuarioRequest usuarioRequest);
}
