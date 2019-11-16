/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.VarCont.Resource.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author OkamotoPc
 */
public class ProdutoResource {
    
    @JsonProperty ("prod_nome")
    private String nomeProd; 
    
    @JsonProperty ("prod_valor_compra")
    private String valorCompra; 
    
    @JsonProperty ("prod_valor_venda")
    private String valorVenda; 

    public ProdutoResource(String nomeProd, String valorCompra, String valorVenda) {
        this.nomeProd = nomeProd;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public String getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(String valorCompra) {
        this.valorCompra = valorCompra;
    }

    public String getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(String valorVenda) {
        this.valorVenda = valorVenda;
    }

 
    @Override
    public String toString()
            {
                return "produtoResource[nome=" +nomeProd+ ", valor da compra"+ valorCompra+ ", valor da venda " +valorVenda+"]";
            }
}
