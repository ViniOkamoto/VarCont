package br.com.fatec.VarCont.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.VarCont.DataSource.Models.Lote;
import br.com.fatec.VarCont.Resource.Models.LoteResource;
import br.com.fatec.VarCont.exceptions.LoteNotFoundException;
import br.com.fatec.VarCont.services.LoteService;

@RestController
@RequestMapping(path = "admin")
public class LoteController {
	
	
	
	@Autowired
	private LoteService serviceLote;
	
	
	
	@GetMapping("lote")
	public List<Lote> buscarLote(){
		return serviceLote.buscarLote();	
	}
	@GetMapping("lote/{id}")
	public Lote buscarLoteId(@PathVariable(name = "id", required = true) Long id ) throws LoteNotFoundException{
		return serviceLote.buscarId(id);
	}
	@PostMapping("lote/criar")
	public void criarLote(@Valid @RequestBody LoteResource loteResource) {
		serviceLote.cadastrarLote(loteResource);
	}
	
	@DeleteMapping("lote/{id}")
	public void deletarId(@PathVariable(name = "id", required = true) Long id ) throws LoteNotFoundException{
		serviceLote.deleteId(id);
	}
	
}
