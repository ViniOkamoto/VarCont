package br.com.fatec.VarCont.Controllers;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Resource.Models.LoteResource;
import br.com.fatec.VarCont.exceptions.LoteNotFoundException;
import br.com.fatec.VarCont.services.LoteService;

@RestController
@RequestMapping(path = "admin")
public class LoteController {
	
	
	
	@Autowired
	private LoteService serviceLote;
	
	@GetMapping
	public ResponseEntity<Object> listaEstoques(HttpSession session) {
		try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			//Confere sessão
			if (usuario != null) {
				if(usuario.isAdmin() != true) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa ser Admin para realizar essa ação");
				}
				return ResponseEntity.ok(serviceLote.listarEstoques());
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar os lotes");
		}
	}
	@GetMapping("contabilidade")
	public ResponseEntity<Object> buscarTabela(HttpSession session){
		try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			//Confere sessão
			if (usuario != null) {
				if(usuario.isAdmin() != true) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa ser Admin para realizar essa ação");
				}
				return ResponseEntity.ok(serviceLote.buscarLote());
			}
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar os lotes");
		}	
	}
	
	@GetMapping("lote")
	public ResponseEntity<Object> buscarLote(HttpSession session){
		try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			//Confere sessão
			if (usuario != null) {
				if(usuario.isAdmin() != true) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa ser Admin para realizar essa ação");
				}
				return ResponseEntity.ok(serviceLote.buscarLote());
			}
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar os lotes");
		}	
	}
	@GetMapping("lote/{id}")
	public ResponseEntity<Object> buscarLoteId(@PathVariable(name = "id", required = true) Long id, HttpSession session ) throws LoteNotFoundException{
			try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			//Confere sessão
			if (usuario != null) {
				if(usuario.isAdmin() != true) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa ser Admin para realizar essa ação");
				}
				return ResponseEntity.ok(serviceLote.buscarId(id));
			}
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar os lotes");
		}
	}
	@PostMapping("lote/criar")
	public ResponseEntity<Object> criarLote(@Valid @RequestBody LoteResource loteResource, HttpSession session) {
		try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			//Confere sessão
			if (usuario != null) {
				if(usuario.isAdmin() != true) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa ser Admin para realizar essa ação");
				}
				serviceLote.cadastrarLote(loteResource);
				return ResponseEntity.ok(null);
			}
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar os lotes");
		}
	}
	
	@DeleteMapping("lote/{id}")
	public ResponseEntity<Object> deletarId(@PathVariable(name = "id", required = true) Long id, HttpSession session ) throws LoteNotFoundException{
		try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			//Confere sessão
			if (usuario != null) {
				if(usuario.isAdmin() != true) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa ser Admin para realizar essa ação");
				}
				serviceLote.deleteId(id);
				return ResponseEntity.ok(null);
			}
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar os lotes");
		}
	}
	
}
