/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.VarCont.services;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Repository.UsuarioRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.fatec.VarCont.Resource.Models.UsuarioResource;
import br.com.fatec.VarCont.exceptions.UsuarioResourceException;
/**
 *
 * @author OkamotoPc
 */
@Service
public class CadastroCaixaService {
    private static final Logger LOG = Logger.getLogger(CadastroCaixaService.class);
	
	@Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private UsuarioConversor serviceConversor;
    
    public void cadastrar(UsuarioResource usuarioResource){
	try {
		Usuario usuario = serviceConversor.conversor(usuarioResource);
    	usuarioRepository.saveAndFlush(usuario);

	} catch (UsuarioResourceException e) {
		LOG.error("Erro em salva o usuario: " + e.getMessage(), e);
	}
       
        
    }

    
}
