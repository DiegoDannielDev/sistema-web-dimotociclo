package br.com.project.dimotocicloapi.adapters.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ValoresProdutosResponse {

    private double valorVendaVarejo;
    private double valorCompra;
    private double valorCusto;
    private double margemVendaVarejo;
    private double valorAtacado;
    private double margemVendaAtacado;
    private Double valorCompraProdutoUnitario;
    private Double valorLucroVendaVarejo;
    private Double valorLucroVendaAtacadado;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataInclusao;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataAlteracao;
}

