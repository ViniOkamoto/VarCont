package br.com.fatec.VarCont.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Repository.UsuarioRepository;
import br.com.fatec.VarCont.Resource.Models.LoginResource;
import br.com.fatec.VarCont.Resource.Models.UsuarioResource;

@RestController
public class SessionController {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping("login")
	@ResponseBody
	public String validarLogin(@RequestBody UsuarioResource userResource , HttpSession session) {
		Usuario usuario =  usuarioRepository.findByEmailAndPassword(userResource.getEmail(), userResource.getSenha() );
		if(!usuario.equals(null)) {
			if(usuario.isAdmin() == true) {
				session.setAttribute("login", usuario);
				return "admin";
			} else {
				session.setAttribute("login", usuario);
				return "caixa";
			}
			}
		return "login";
	}
}
