/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.VarCont.services;

import org.springframework.stereotype.Component;

import br.com.fatec.VarCont.DataSource.Models.Produto;
import br.com.fatec.VarCont.exceptions.ProdutoResourceException;
import br.com.fatec.VarCont.Resource.Models.ProdutoResource;

/**
 *
 * @author OkamotoPc
 */
@Component
public class ProdutoConversor {
	public Produto conversor(ProdutoResource produtoResource) throws ProdutoResourceException {

		try {
			Produto produto = new Produto();
			produto.setNomeProd(produtoResource.getNomeProd());
			produto.setValorCompra(produtoResource.getValorCompra());
			produto.setValorVenda(produtoResource.getValorVenda());
			return produto;

		} catch (Exception e) {
			throw new ProdutoResourceException(
					"Falha ao converter o resource para entidade, resource: " + produtoResource);
		}
	}

}
