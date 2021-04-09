package dev.dias375.os.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import dev.dias375.os.domain.model.StatusOrdemServico;

public class OrdemServicoRepresentationModel {

	private Long id;
	private String descricao;
	private BigDecimal preco;
	private StatusOrdemServico status;
	private LocalDateTime dataAbertura;
	private LocalDateTime dataFinalizacao;
	private ClienteResumoRepresentationModel cliente;

	public ClienteResumoRepresentationModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteResumoRepresentationModel cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public StatusOrdemServico getStatus() {
		return status;
	}

	public void setStatus(StatusOrdemServico status) {
		this.status = status;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

}
