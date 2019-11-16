package br.com.fatec.VarCont.services;

import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Repository.UsuarioRepository;
import br.com.fatec.VarCont.Resource.Models.UsuarioResource;
import br.com.fatec.VarCont.exceptions.UsuarioNotFoundException;
import br.com.fatec.VarCont.exceptions.UsuarioResourceException;

@Service
public class CaixaService {

	private static final Logger LOG = Logger.getLogger(CaixaService.class);

	 @Autowired
	    private UsuarioConversor serviceConversor;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	private Optional<Usuario> getOptional(Long id) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		return optionalUsuario;
	}

	public List<Usuario> buscarCaixa() {
		LOG.info("Serviço para buscar os caixas, sendo executado");
		List<Usuario> listaCaixas = usuarioRepository.findAll();

		return listaCaixas;
	}

	public Usuario buscarId(Long id) throws UsuarioNotFoundException {
		Optional<Usuario> optionalUsuario = getOptional(id);
		Usuario usuario = null;
		if (!optionalUsuario.isPresent()) {
			throw new UsuarioNotFoundException("Usuario não encontrado através do ID: " + id);
		} else {
			usuario = optionalUsuario.get();
		}
		LOG.info("Serviço para buscar caixa, sendo executado");
		return usuario;
	}

	public void deletarId(Long id) throws UsuarioNotFoundException {
		Optional<Usuario> optionalUsuario = getOptional(id);
		if (!optionalUsuario.isPresent()) {
			throw new UsuarioNotFoundException("Usuario não encontrado através do ID: " + id);
		} else {
			LOG.info("Serviço para deletar caixa, sendo executado");
			usuarioRepository.delete(optionalUsuario.get());
		}
	}
    
	public void cadastrar(UsuarioResource usuarioResource){
	try {
		LOG.info("Serviço para criar caixa, sendo executado");
		Usuario usuario = serviceConversor.conversor(usuarioResource);
    	usuarioRepository.saveAndFlush(usuario);

	} catch (UsuarioResourceException e) {
		LOG.error("Erro em salva o usuario: " + e.getMessage(), e);
	}
       
	
}
}