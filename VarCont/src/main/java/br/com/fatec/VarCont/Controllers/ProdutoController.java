package br.com.fatec.VarCont.Controllers;

import br.com.fatec.VarCont.DataSource.Models.Produto;
import br.com.fatec.VarCont.DataSource.Models.Usuario;
import br.com.fatec.VarCont.Resource.Models.ProdutoResource;
import br.com.fatec.VarCont.exceptions.ProdutoNotFoundException;
import br.com.fatec.VarCont.services.ProdutoService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProdutoController {
    
      
    @Autowired
    private ProdutoService serviceProduto;
    
    
    @GetMapping ("produto")
    public ResponseEntity<Object> buscarProduto(HttpSession session)
    {		Usuario usuario = (Usuario) session.getAttribute("login");
			//Confere sessão
			if (usuario != null) {
				return ResponseEntity.ok(serviceProduto.buscarProduto());
			}
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Você precisa estar logado");
		
	}
	
    
    @GetMapping("produto/{id}")
	public ResponseEntity<Object> buscarProdutoId(@PathVariable(name = "id", required = true) Long id,HttpSession session){
	try {	Usuario usuario = (Usuario) session.getAttribute("login");
		//Confere sessão
		if (usuario != null) {
			if(usuario.isAdmin() != true) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa ser Admin para realizar essa ação");
			}
			return ResponseEntity.ok(serviceProduto.buscarId(id));
		}
		return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Você precisa estar logado");
	} catch (Exception e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
	}
    }
    
    @PostMapping("produto/criar")
	public ResponseEntity<Object> criarProduto(@Valid @RequestBody ProdutoResource produtoResource,HttpSession session) {
		try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			//Confere sessão
			if (usuario != null) {
				if(usuario.isAdmin() != true) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa ser Admin para realizar essa ação");
				}
				serviceProduto.cadastrarProduto(produtoResource);
				return ResponseEntity.ok(null);
			}
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
		}
	}
    
    @DeleteMapping("produto/{id}")
	public ResponseEntity<Object> deleteProdutoId(@PathVariable(name = "id", required = true) Long id, HttpSession session) throws  ProdutoNotFoundException {
		try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			//Confere sessão
			if (usuario != null) {
				if(usuario.isAdmin() != true) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa ser Admin para realizar essa ação");
				}
				serviceProduto.deleteId(id);
				return ResponseEntity.ok(null);
			}
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
		}
	}
    
    @PutMapping("produto/{id}")
    public ResponseEntity<Object> alterarProduto(@PathVariable(name = "id", required = true) Long id,@Valid @RequestBody Produto produto, HttpSession session){
    	try {
			Usuario usuario = (Usuario) session.getAttribute("login");
			//Confere sessão
			if (usuario != null) {
				if(usuario.isAdmin() != true) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Você precisa ser Admin para realizar essa ação");
				}
				serviceProduto.alterarProduto(id, produto);
				return ResponseEntity.ok(null);
			}
			return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Você precisa estar logado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
		}
	}
	 	    	
    }


    
    
    
    

