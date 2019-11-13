/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.VarCont.services;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author OkamotoPc
 */
public class CadastroUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public void cadastrar(Usuario usuario){
        usuarioRepository.saveAndFlush(usuario);
        
    }
//    public void buscarPorId(){
//        Optional<Usuario> user = usuarioRepository.findById(1L);
//        Usuario usuario = user.get();
//    }
    
}
