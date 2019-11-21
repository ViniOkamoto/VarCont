package br.com.fatec.VarCont.services;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fatec.VarCont.DataSource.Models.Lote;
import br.com.fatec.VarCont.Repository.LoteRepository;
import br.com.fatec.VarCont.Resource.Models.LoteResource;
import br.com.fatec.VarCont.exceptions.LoteNotFoundException;
import br.com.fatec.VarCont.exceptions.LoteResourceException;

@Service
public class LoteService {
	private static final Logger LOG = Logger.getLogger(LoteService.class);
	@Autowired
	private LoteRepository loteRepository;

	@Autowired
	private LoteConversor serviceConversor;

	private Optional<Lote> getOptional(Long id) {
		Optional<Lote> optionalLote = loteRepository.findById(id);
		return optionalLote;
	}

	public List<Lote> buscarLote() {

		LOG.info("Serviço para buscar os lotes, sendo executado");
		List<Lote> listaLotes = loteRepository.findAll();
		return listaLotes;
	}

	public Lote buscarId(Long id) throws LoteNotFoundException {
		Optional<Lote> optionalLote = getOptional(id);
		Lote lote = null;
		if (!optionalLote.isPresent()) {
			throw new LoteNotFoundException("Lote não encontrado através do ID: " + id);
		} else {
			lote = optionalLote.get();
		}
		LOG.info("Serviço para buscar por id sendo executado");
		return lote;
	}

	public void cadastrarLote(LoteResource loteResource) {
		try {
			LOG.info("Serviço para criar lote, sendo executado");
			Lote lote = serviceConversor.conversor(loteResource);
			loteRepository.saveAndFlush(lote);
		} catch (LoteResourceException e) {
			LOG.error("Erro em salvar o lote " + e.getMessage(), e);
		}
	}

	public void deleteId(Long id) throws LoteNotFoundException {
		Optional<Lote> optionalLote = getOptional(id);
		if (!optionalLote.isPresent()) {
			throw new LoteNotFoundException(" Lote não encontrado através do ID: " + id);
		} else {
			LOG.info("Serviço para deletar lote, sendo realizado");
			loteRepository.delete(optionalLote.get());
		}
	}
	
	public List<Object> listarEstoques(){
		return loteRepository.findEstoqueTotais();
	}
}