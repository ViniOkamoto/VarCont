package br.com.fatec.VarCont.Controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Resource.Models.VendaResource;
import br.com.fatec.VarCont.services.VendaService;

@RestController
public class VendaController {
	@Autowired
	private VendaService serviceVenda;

	@GetMapping("venda")
	public ResponseEntity<Object> listarVendas(HttpSession session){
		try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			//Confere sessão
			if (usuario != null) {
				if(usuario.isAdmin() != true) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa ser Admin para realizar essa ação");
				}
				return ResponseEntity.ok(serviceVenda.buscarVendas());
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar as vendas");
		}
	}
	@GetMapping("venda/user")
	public ResponseEntity<Object> listarMinhasVendas(HttpSession session){
		try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			//Confere sessão
			if (usuario != null) {
				Long id = usuario.getId();
				return ResponseEntity.ok(serviceVenda.buscarVendasUser(id));
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda não encontrada");
		}
	}
	@PostMapping("venda")
	public ResponseEntity<Object> cadastrarVenda(@Valid @RequestBody VendaResource vendaResource, HttpSession session) {
		try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			if (usuario != null) {
				Long id = usuario.getId();
				serviceVenda.criarVenda(vendaResource, id);
				return ResponseEntity.ok(null);
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível realizar a venda");
		}
	}
	@PostMapping("venda")
	public ResponseEntity<Object> deletarVenda(@Valid @RequestBody VendaResource vendaResource, HttpSession session) {
		try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			if (usuario != null) {
				Long id = usuario.getId();
				serviceVenda.criarVenda(vendaResource, id);
				return ResponseEntity.ok(null);
			}
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível realizar a venda");
		}
	}
}