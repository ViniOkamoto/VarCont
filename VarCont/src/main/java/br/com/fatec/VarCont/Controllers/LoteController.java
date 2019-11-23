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
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.VarCont.Resource.Models.LoteResource;
import br.com.fatec.VarCont.exceptions.LoteNotFoundException;
import br.com.fatec.VarCont.services.LoteService;

@RestController
public class LoteController {
	
	
	
	@Autowired
	private LoteService serviceLote;
	
	@GetMapping("estoque")
	public ResponseEntity<Object> listaEstoques(HttpSession session) {
		try {
				return ResponseEntity.ok(serviceLote.listarEstoques());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar os lotes");
		}
	}
	
	@GetMapping("lote")
	public ResponseEntity<Object> buscarLote(HttpSession session){
		try {
				return ResponseEntity.ok(serviceLote.buscarLote());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar os lotes");
		}	
	}
	@GetMapping("lote/{id}")
	public ResponseEntity<Object> buscarLoteId(@PathVariable(name = "id", required = true) Long id, HttpSession session ) throws LoteNotFoundException{
			try {
				return ResponseEntity.ok(serviceLote.buscarId(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar os lotes");
		}
	}
	@PostMapping("lote/criar")
	public ResponseEntity<Object> criarLote(@Valid @RequestBody LoteResource loteResource, HttpSession session) {
		try {
				serviceLote.cadastrarLote(loteResource);
				return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar os lotes");
		}
	}
	
	@DeleteMapping("lote/{id}")
	public ResponseEntity<Object> deletarLote(@PathVariable(name = "id", required = true) Long id, HttpSession session ) throws LoteNotFoundException{
		try {
				serviceLote.deleteId(id);
				return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar os lotes");
		}
	}
	
}
