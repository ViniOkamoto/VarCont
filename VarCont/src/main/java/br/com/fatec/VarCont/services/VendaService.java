package br.com.fatec.VarCont.services;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fatec.VarCont.DataSource.Models.Venda;
import br.com.fatec.VarCont.Repository.VendaRepository;
import br.com.fatec.VarCont.Resource.Models.VendaResource;
import br.com.fatec.VarCont.exceptions.VendaNotFoundException;
import br.com.fatec.VarCont.exceptions.VendaResourceException;

@Service
public class VendaService {
	private static final Logger LOG = Logger
            .getLogger(LoteService.class);
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private VendaConversor serviceConversor;
	
	public List<Venda> buscarVendas(){
		LOG.info("Serviço de listagem de vendas, sendo realizado");
		List<Venda> listaVenda = vendaRepository.findAll();
		return listaVenda;
	}
	public List<Venda> buscarVendasUser(Long id) throws VendaNotFoundException{
		LOG.info("Serviço de listagem de vendas, sendo realizado");
		List<Venda> listaVenda = vendaRepository.findByUserId(id);
		if(listaVenda.equals(null)) {
			throw new VendaNotFoundException("Venda feita pelo usuário não encontrada");
		}
		return listaVenda;
	}
	
	public void criarVenda(VendaResource vendaResource) {
		try {
			LOG.info("Serviço de criação de venda, sendo executado");
			Venda venda = serviceConversor.conversor(vendaResource);
			vendaRepository.saveAndFlush(venda);
		}catch(VendaResourceException e){
			LOG.error("Erro em salvar o produto" + e.getMessage(), e);
		}
	}
//	public void deletarId(Long id) {
//		try {
//			LOG.info("Serviço de criação de venda, sendo executado");
//			Venda venda = serviceConversor.conversor(vendaResource);
//			vendaRepository.saveAndFlush(venda);
//		}catch(VendaResourceException e){
//			LOG.error("Erro em salvar o produto" + e.getMessage(), e);
//		}
//	}
}
