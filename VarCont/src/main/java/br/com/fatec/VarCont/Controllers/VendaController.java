package br.com.fatec.VarCont.Controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.VarCont.Resource.Models.VendaResource;
import br.com.fatec.VarCont.services.VendaService;

@RestController
public class VendaController {
	@Autowired
	private VendaService serviceVenda;

	@GetMapping("venda")
	public ResponseEntity<Object> listarVendas(HttpSession session){
		try {
				return ResponseEntity.ok(serviceVenda.buscarVendas());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar as vendas");
		}
	}

	@PostMapping("venda")
	public ResponseEntity<Object> cadastrarVenda(@Valid @RequestBody VendaResource vendaResource, HttpSession session) {
		try {
				serviceVenda.criarVenda(vendaResource);
				return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível realizar a venda");
		}
	}
	
//	@DeleteMapping("venda")
//	public ResponseEntity<Object> deletarVenda(@Valid @RequestBody VendaResource vendaResource, HttpSession session) {
//		try {
//				serviceVenda.criarVenda(vendaResource);
//				return ResponseEntity.ok(null);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível realizar a venda");
//		}
//	}
}