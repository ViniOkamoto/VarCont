package br.com.fatec.VarCont.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Resource.Models.UsuarioResource;
import br.com.fatec.VarCont.services.UsuarioService;
import br.com.fatec.VarCont.services.UsuarioConversor;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "admin")
public class UserControllers {

	@Autowired
	UsuarioConversor serviceConversor;

	@Autowired
	private UsuarioService serviceUsuario;


	@GetMapping(path = "/usuario")
	public ResponseEntity<Object> buscarCaixa(HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("login");
		if (usuario != null) {
			if(usuario.isAdmin() != true) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa ser Admin para realizar essa ação");
			}
			return ResponseEntity.ok(serviceUsuario.buscarUsuario());
		}
		return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Você precisa estar logado");

	}

	@GetMapping("usuario/{id}")
	public ResponseEntity<Object> buscarCaixaId(@PathVariable(name = "id", required = true) Long id, HttpSession session) {
		try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			// Confere sessão
			if (usuario != null) {
				if (usuario.isAdmin() != true) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
							.body("Você precisa ser Admin para realizar essa ação");
				}
				return ResponseEntity.ok(serviceUsuario.buscarId(id));
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
	}
		
	}

	@PostMapping("usuario/criar")
	public ResponseEntity<Object> criarCaixa(@Valid @RequestBody UsuarioResource usuarioResource, HttpSession session) {
		try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			// Confere sessão
			if (usuario != null) {
				if (usuario.isAdmin() != true) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
							.body("Você precisa ser Admin para realizar essa ação");
				}
				serviceUsuario.cadastrarUsuario(usuarioResource);
				return ResponseEntity.ok(null);
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
	}
	}

	@DeleteMapping("usuario/{id}")
	public ResponseEntity<Object> deleteCaixa(@PathVariable(name = "id", required = true) Long id, HttpSession session) {
		try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			// Confere sessão
			if (usuario != null) {
				if (usuario.isAdmin() != true) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
							.body("Você precisa ser Admin para realizar essa ação");
				}
				serviceUsuario.deletarId(id);
				return ResponseEntity.ok(null);
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
	}	
}
	@PutMapping("usuario/{id}")
	public ResponseEntity<Object> alterarCaixa(@PathVariable Long id, @RequestBody Usuario usuario,
			HttpSession session) {
		try {
			Usuario usuarioSessão = (Usuario) session.getAttribute("login");
			// Confere sessão
			if (usuarioSessão != null) {
				if (usuario.isAdmin() != true) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
							.body("Você precisa ser Admin para realizar essa ação");
				}
				serviceUsuario.alterarUsuario(usuario, id);
				return ResponseEntity.ok(null);
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
		}

	}

}
