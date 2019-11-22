package br.com.fatec.VarCont.Controllers;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Resource.Models.VendaResource;
import br.com.fatec.VarCont.services.LoteService;
import br.com.fatec.VarCont.services.ProdutoService;
import br.com.fatec.VarCont.services.VendaService;

@RestController
@RequestMapping(path = "caixa")
public class CaixaController {

	@Autowired
	private LoteService serviceLote;

	@Autowired
	private ProdutoService serviceProduto;

	@Autowired
	private VendaService serviceVenda;

	@GetMapping
	public ResponseEntity<Object> listarLote(HttpSession session) {
		try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			if (usuario != null) {
				return ResponseEntity.ok(serviceLote.listarEstoques());
			}
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar os lotes");
		}
	}

	@GetMapping
	public ResponseEntity<Object> listarProduto(HttpSession session){
		try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			if (usuario != null) {
				return ResponseEntity.ok(serviceProduto.buscarProduto());
			}
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível encontrar os produtos");
		}
	}


	@GetMapping("vendas")
	public ResponseEntity<Object> buscarVendasUser(HttpSession session) {
		try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			if (usuario != null) {
				Long id = usuario.getId();
				return ResponseEntity.ok(serviceVenda.buscarVendasUser(id));
			}
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
		}
	}

	@PostMapping
	public ResponseEntity<Object> cadastrarVenda(@Valid @RequestBody VendaResource vendaResource, HttpSession session) {
		try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			if (usuario != null) {
				Long id = usuario.getId();
				serviceVenda.criarVenda(vendaResource, id);
				return ResponseEntity.ok(null);
			}
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível criar o usuário");
		}
	}
}
