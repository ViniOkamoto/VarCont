package br.com.fatec.VarCont.services;

import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatec.VarCont.DataSource.Models.Lote;
import br.com.fatec.VarCont.DataSource.Models.Produto;
import br.com.fatec.VarCont.Repository.ProdutoRepository;
import br.com.fatec.VarCont.Resource.Models.LoteResource;
import br.com.fatec.VarCont.exceptions.LoteResourceException;

@Component
public class LoteConversor {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Lote conversor (LoteResource loteResource) throws LoteResourceException{
		
		try {
			Optional<Produto> optionalProduto = produtoRepository.getById(loteResource.getIdProduto());
			Produto produto = new Produto();
			produto = optionalProduto.get();
			Lote lote = new Lote();
			Date date = new Date(); 
			int qtdCompra = checkCompra(loteResource.getQtdCompra());
			int qtdTotal = checkTotal(loteResource.getQtdTotal());
			lote.setData(date);
			lote.setIdProduto(produto);
			lote.setQtdCompra(qtdCompra);
			lote.setQtdTotal(qtdTotal);
			return lote;
		}catch(Exception e) {
			throw new LoteResourceException(
					"Falha ao converter o resource para entidade, resource: " + loteResource);
			
		}
		
	}
	private int checkCompra(String num) {
		return Integer.parseInt(num);
	}
	private int checkTotal(String num) {
		return Integer.parseInt(num);
	}
}
