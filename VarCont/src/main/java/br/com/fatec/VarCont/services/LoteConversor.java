package br.com.fatec.VarCont.services;

import org.springframework.stereotype.Component;
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
			Optional<Produto> optionalProduto = produtoRepository.findById(loteResource.getIdProduto());
			Produto produto = new Produto();
			produto = optionalProduto.get();
			Lote lote = new Lote();
			int qtdCompra = checkCompra(loteResource.getQtdCompra());
			lote.setProduto(produto);
			lote.setQtdCompra(qtdCompra);
			lote.setQtdTotal(qtdCompra);
			return lote;
		}catch(Exception e) {
			throw new LoteResourceException(
					"Falha ao converter o resource para entidade, resource: " + loteResource);
			
		}
		
	}
	private int checkCompra(String num) {
		return Integer.parseInt(num);
	}
}
