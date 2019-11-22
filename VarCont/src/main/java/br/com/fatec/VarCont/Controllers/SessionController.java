package br.com.fatec.VarCont.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Repository.UsuarioRepository;
import br.com.fatec.VarCont.Resource.Models.LoginResource;

@RestController
public class SessionController {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping("login")
	@ResponseBody
	public ResponseEntity<Object> validarLogin(@RequestBody LoginResource loginResource , HttpSession session) {
		try{
			Usuario usuario =  usuarioRepository.findByEmailAndPassword(loginResource.getEmail(), loginResource.getSenha() );
			if(!usuario.equals(null)) {
			if(usuario.isAdmin() == true) {
				session.setAttribute("login", usuario);
				return ResponseEntity.ok("admin");
			} else {
				session.setAttribute("login", usuario);
				return ResponseEntity.ok("caixa");
			}
			}
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return null;
	}
}
