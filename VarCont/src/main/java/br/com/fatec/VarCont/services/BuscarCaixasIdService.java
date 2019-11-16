package br.com.fatec.VarCont.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Repository.UsuarioRepository;
import br.com.fatec.VarCont.exceptions.UsuarioNotFoundException;

@Service
public class BuscarCaixasIdService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	private Optional<Usuario> getOptional(Long id) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		return optionalUsuario;
	}

	public Usuario buscarId(Long id) throws UsuarioNotFoundException {
		Optional<Usuario> optionalUsuario = getOptional(id);
		Usuario usuario = null;
		if (!optionalUsuario.isPresent()) {
			throw new UsuarioNotFoundException("Usuario não encontrado através do ID: " + id);
		} else {
			usuario = optionalUsuario.get();
		}
		return usuario;
	}

	public void deletarId(Long id) throws UsuarioNotFoundException {
		Optional<Usuario> optionalUsuario = getOptional(id);
		if (!optionalUsuario.isPresent()) {
			throw new UsuarioNotFoundException("Usuario não encontrado através do ID: " + id);
		} else {
			usuarioRepository.delete(optionalUsuario.get());
		}
	}

}