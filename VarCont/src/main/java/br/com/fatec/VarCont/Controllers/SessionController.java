package br.com.fatec.VarCont.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Repository.UsuarioRepository;

@RestController
public class SessionController {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping("login")
	public String validarLogin(@RequestBody String email,@RequestBody String senha, HttpSession session) {
		Usuario usuario =  usuarioRepository.findByEmailAndPassword(email, senha);
		if(usuario != null) {
			if(usuario.isAdmin() == true) {
				session.setAttribute("login", usuario);
				return "admin";
			}else {
				session.setAttribute("login", usuario);
				return "caixa";
			}
		}
		return "login";
	}
}
