package br.com.fatec.VarCont.Resource.Models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LoteResource {
	 // Aqui é onde decidimos o que colocaremos na requisição para api.
	@JsonProperty("prod_id")
	private Long idProduto;

	@JsonProperty("lote_qtdCompra")
	private String qtdCompra;
	
	@JsonProperty("lote_qtdTotal")
	private String qtdTotal;


	public LoteResource(Long idProduto, String qtdCompra, String qtdTotal) {
		this.idProduto = idProduto;
		this.qtdCompra = qtdCompra;
		this.qtdTotal = qtdTotal;
	}




	public Long getIdProduto() {
		return idProduto;
	}




	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}




	public String getQtdCompra() {
		return qtdCompra;
	}




	public void setQtdCompra(String qtdCompra) {
		this.qtdCompra = qtdCompra;
	}




	public String getQtdTotal() {
		return qtdTotal;
	}




	public void setQtdTotal(String qtdTotal) {
		this.qtdTotal = qtdTotal;
	}




	@Override
	public String toString() {
		return "LoteResource [idProduto=" + idProduto + ", qtdCompra=" + qtdCompra + ", qtdTotal=" + qtdTotal + "]";
	}

}
