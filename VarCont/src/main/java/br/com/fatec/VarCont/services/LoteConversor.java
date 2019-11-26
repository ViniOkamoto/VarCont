package br.com.fatec.VarCont.services;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatec.VarCont.DataSource.Models.Lote;
import br.com.fatec.VarCont.DataSource.Models.Produto;
import br.com.fatec.VarCont.Repository.LoteRepository;
import br.com.fatec.VarCont.Repository.ProdutoRepository;
import br.com.fatec.VarCont.Resource.Models.LoteResource;
import br.com.fatec.VarCont.exceptions.LoteNotFoundException;
import br.com.fatec.VarCont.exceptions.LoteResourceException;

@Component
public class LoteConversor {
	@Autowired
	private ProdutoRepository produtoRepository;
		
	@Autowired
	private LoteRepository loteRepository;
	
	public Lote conversor (LoteResource loteResource) throws LoteResourceException{
		
		try {
			Optional<Produto> optionalProduto = produtoRepository.findById(loteResource.getIdProduto());
			Produto produto = new Produto();
			produto = optionalProduto.get();
			Lote lote = new Lote();
			int qtdCompra = checkCompra(loteResource.getQtdCompra());
			lote.setData(new Date());
			lote.setProduto(produto);
			lote.setQtdCompra(qtdCompra);
			lote.setQtdTotal(qtdCompra);
			return lote;
		}catch(Exception e) {
			throw new LoteResourceException(
					"Falha ao converter o resource para entidade, resource: " + loteResource);
			
		}
		
	}

	public Lote conversorAltera(LoteResource loteResource, Long id) throws LoteResourceException{
		
		try {
			Optional<Lote> optionalLote= loteRepository.findById(id);
			if(!optionalLote.isPresent()) {
				throw new LoteNotFoundException("Lote não encontrado através do ID:" + id);
			}
			Lote lote = optionalLote.get();
			if(lote.getQtdTotal()!= lote.getQtdCompra()) {
				throw new Exception("Não é possível alterar um lote já consumido");
			}
			if(loteResource.getQtdCompra() != null) {
				int qtdCompra = checkCompra(loteResource.getQtdCompra());
				lote.setQtdCompra(qtdCompra);
				lote.setQtdTotal(qtdCompra);
			}
			if(loteResource.getIdProduto() != null) {
				Optional<Produto> optionalProduto = produtoRepository.findById(loteResource.getIdProduto());
				Produto produto = optionalProduto.get();
				lote.setProduto(produto);
			}
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
