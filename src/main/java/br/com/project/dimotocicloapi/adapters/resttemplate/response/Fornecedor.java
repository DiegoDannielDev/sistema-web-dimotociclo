package br.com.project.dimotocicloapi.adapters.resttemplate.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Fornecedor {

  private Long codigoFornecedor;
  private String nome;
  private String nomeFantasia;
  private String cpf;
  private String cnpj;
  private String tipo;
  private String situacao;
  private String endereco;
  private String cep;
  private List<String> numerosCtt;
}
