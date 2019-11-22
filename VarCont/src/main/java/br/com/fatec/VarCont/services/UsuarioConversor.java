/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.VarCont.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Repository.UsuarioRepository;
import br.com.fatec.VarCont.exceptions.UsuarioResourceException;
import br.com.fatec.VarCont.Resource.Models.UsuarioResource;

/**
 *
 * @author OkamotoPc
 */
@Component
public class UsuarioConversor {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario conversor(UsuarioResource usuarioResource) throws UsuarioResourceException {

		try {
			checkEmail(usuarioResource.getEmail());
			Usuario usuario = new Usuario();
			Boolean adminstrador = checkAdminstrador(usuarioResource.getAdmin());
			usuario.setEmail(usuarioResource.getEmail());
			usuario.setNome(usuarioResource.getNome());
			usuario.setSenha(usuarioResource.getSenha());
			usuario.setAdmin(adminstrador);
			return usuario;
			
		} catch (Exception e) {
			throw new UsuarioResourceException(
					"Falha ao converter o resource para entidade, resource: "
								+ usuarioResource + " " + e);
		}
	}
		private Boolean checkAdminstrador(String adminstrador) {
			return Boolean.parseBoolean(adminstrador);
		}
		public void checkEmail(String email) {
			Optional<Usuario> optionalEmail = usuarioRepository.findByEmail(email);
			if(optionalEmail.isPresent()) {
				throw new Error("Já existe um usuário com este email");
			}
		}
}
