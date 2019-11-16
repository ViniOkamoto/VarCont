/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.VarCont.services;
import org.springframework.stereotype.Component;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.exceptions.UsuarioResourceException;
import br.com.fatec.VarCont.Resource.Models.UsuarioResource;

/**
 *
 * @author OkamotoPc
 */
@Component
public class UsuarioConversor {
	public Usuario conversor(UsuarioResource usuarioResource) throws UsuarioResourceException {

		try {
			Usuario usuario = new Usuario();
			Boolean adminstrador = checkAdminstrador(usuarioResource.getAdministrador());
			usuario.setEmail(usuarioResource.getEmail());
			usuario.setNome(usuarioResource.getNome());
			usuario.setSenha(usuarioResource.getSenha());
			usuario.setAdmin(adminstrador);
			return usuario;
			
		} catch (Exception e) {
			throw new UsuarioResourceException(
					"Falha ao converter o resource para entidade, resource: "
								+ usuarioResource);
		}
	}
		private Boolean checkAdminstrador(String adminstrador) {
			return Boolean.parseBoolean(adminstrador);
		}
}
