package br.com.project.dimotocicloapi.domain.useCase;

import br.com.project.dimotocicloapi.adapters.Telas;
import br.com.project.dimotocicloapi.adapters.resttemplate.impl.UsuarioLoginRestImpl;
import br.com.project.dimotocicloapi.domain.mappers.UsuarioLoginMapper;
import br.com.project.dimotocicloapi.domain.model.UsuarioDTO;
import br.com.project.dimotocicloapi.port.UsuarioLoginPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioLoginUseCase {

  private static UsuarioLoginPort usuarioLoginPort = new UsuarioLoginRestImpl();
  private final ValidaAcessos validaAcesso = new ValidaAcessos();
  private static UsuarioLoginUseCase usuarioLoginUseCase = null;

  public  UsuarioLoginUseCase createUsuarioLoginUseCase() {
    usuarioLoginPort = new UsuarioLoginRestImpl();
    return usuarioLoginUseCase = new UsuarioLoginUseCase();
  }

  public UsuarioDTO validarUsuario(UsuarioDTO usuarioDTO) {
    var user = UsuarioLoginMapper.toRequest(usuarioDTO);
    assert user != null;
    var userRest =
        UsuarioLoginMapper.toDto(usuarioLoginPort.validarUsuario(user.getLogin(), user.getSenha()));
    assert userRest != null;
    if (validaAcesso.validaAcesso((long) userRest.getCodigo(), Telas.LOGIN.name())) {
      return userRest;
    }
    userRest.setMsgLogin("Usuario Sem acesso a tela");
    return userRest;
  }

  public List<UsuarioDTO> buscarTodos() {
    return UsuarioLoginMapper.toDto(usuarioLoginPort.buscarTodos());
  }

  public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
    return UsuarioLoginMapper.toDto(
        usuarioLoginPort.salvarUsuario(UsuarioLoginMapper.toRequest(usuarioDTO)));
  }
}
