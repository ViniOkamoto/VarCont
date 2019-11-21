package br.com.fatec.VarCont.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.DataSource.Models.Venda;
import br.com.fatec.VarCont.Resource.Models.VendaResource;
import br.com.fatec.VarCont.exceptions.VendaNotFoundException;
import br.com.fatec.VarCont.services.LoteService;
import br.com.fatec.VarCont.services.VendaService;
@RestController
@RequestMapping(path= "caixa")
public class CaixaController {
	
	@Autowired
	private LoteService serviceLote;
	
	@Autowired
	private VendaService serviceVenda;
	
	@GetMapping
	public List<Object> listarLote(HttpSession session) {
		Usuario usuario = (Usuario)session.getAttribute("login");
		if(usuario !=null) {
			return serviceLote.listarEstoques();
			} else {
				throw new Error("Você precisa estar logado");
			}
		}

	
//	
//	@GetMapping("teste")
//	public Long funciona(HttpSession session) {
//		Usuario usuario = (Usuario)session.getAttribute("login");
//		Long id = usuario.getId();
//		return id;
//	}
//	
	@GetMapping("vendas")
	public List<Venda> buscarVendasUser(HttpSession session) {
		Usuario usuario = (Usuario)session.getAttribute("login");
		if(usuario != null) {
		Long id = usuario.getId();
		try {
			return serviceVenda.buscarVendasUser(id);
		} catch (VendaNotFoundException e) {
			e.printStackTrace();
		}
		}
		throw new Error("Você precisa estar logado");
	}
	
	@PostMapping
	public void cadastrarVenda(@Valid @RequestBody VendaResource vendaResource, HttpSession session ) {
		Usuario usuario = (Usuario)session.getAttribute("login");
		if(usuario !=null) {
		Long id = usuario.getId();
		serviceVenda.criarVenda(vendaResource, id);
		} else {
			throw new Error("Você precisa estar logado");
		}
	}
		
}
