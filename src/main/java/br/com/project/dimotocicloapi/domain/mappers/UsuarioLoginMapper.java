package br.com.project.dimotocicloapi.domain.mappers;

import br.com.project.dimotocicloapi.adapters.resttemplate.request.UsuarioRequest;
import br.com.project.dimotocicloapi.domain.model.UsuarioDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class UsuarioLoginMapper {

  public static UsuarioRequest toRequest(UsuarioDTO usuarioDTO) {
    if (Objects.isNull(usuarioDTO)) {
      return null;
    }
    return UsuarioRequest.builder()
        .login(usuarioDTO.getLogin())
        .nome(usuarioDTO.getNome())
        .codigo(usuarioDTO.getCodigo())
        .email(usuarioDTO.getEmail())
        .senha(usuarioDTO.getSenha())
        .build();
  }

  public static List<UsuarioRequest> toRequest(List<UsuarioDTO> usuarioDTO) {
    return usuarioDTO.stream().map(UsuarioLoginMapper::toRequest).collect(Collectors.toList());
  }

  public static UsuarioDTO toDto(UsuarioRequest usuarioDTO) {
    if (Objects.isNull(usuarioDTO)) {
      return null;
    }
    return UsuarioDTO.builder()
        .login(usuarioDTO.getLogin())
        .nome(usuarioDTO.getNome())
        .codigo(usuarioDTO.getCodigo())
        .email(usuarioDTO.getEmail())
        .senha(usuarioDTO.getSenha())
        .build();
  }

  public static List<UsuarioDTO> toDto(List<UsuarioRequest> usuarioDTO) {
    return usuarioDTO.stream().map(UsuarioLoginMapper::toDto).collect(Collectors.toList());
  }
}
