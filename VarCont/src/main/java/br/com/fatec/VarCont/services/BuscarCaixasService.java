/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.VarCont.services;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Repository.UsuarioRepository;
import java.util.List;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author OkamotoPc
 */
@Service
public class BuscarCaixasService {
     
    private static final Logger LOG = Logger
            .getLogger(BuscarCaixasService.class);
    
    @Autowired     
     private UsuarioRepository usuarioRepository;
    
     
     public List<Usuario> buscarCaixa() {
        LOG.info("Serviço para buscar os caixas, sendo executado");
         List<Usuario> listaCaixas = usuarioRepository.findAll();
        
        return listaCaixas;
     }
}
