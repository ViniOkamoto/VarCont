/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.VarCont.Controllers;

import br.com.fatec.VarCont.DataSource.Models.Produto;
import br.com.fatec.VarCont.Repository.ProdutoRepository;
import br.com.fatec.VarCont.Resource.Models.ProdutoResource;
import br.com.fatec.VarCont.exceptions.ProdutoNotFoundException;
import br.com.fatec.VarCont.exceptions.UsuarioNotFoundException;
import br.com.fatec.VarCont.services.ProdutoService;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping (path = "admin")
public class ProdutoController {
    
      
    @Autowired
    private ProdutoService serviceProduto;
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @GetMapping ("produto")
    public List<Produto> buscarProduto()
    {
        return serviceProduto.buscarProduto();
    }
    
    @GetMapping("produto/{id}")
	public Produto buscarProdutoId(@PathVariable(name = "id", required = true) Long id) throws UsuarioNotFoundException, ProdutoNotFoundException {
		return serviceProduto.buscarId(id);
	}
    
    @PostMapping("produto/criar")
	public void criarProduto(@Valid @RequestBody ProdutoResource produtoResource) {

		serviceProduto.cadastrarProduto(produtoResource);
	}
    
    @DeleteMapping("produto/{id}")
	public void deleteProdutoId(@PathVariable(name = "id", required = true) Long id) throws UsuarioNotFoundException, ProdutoNotFoundException {
		serviceProduto.deleteId(id);
	}
    
    @PutMapping("produto/{id}")
    public ResponseEntity<Produto> alterarProduto(@PathVariable(name = "id", required = true) Long id,@Valid @RequestBody Produto produto){
    	
    	Optional<Produto> produtoOptional = produtoRepository.findById(id);
    	if(!produtoOptional.isPresent()) {
    		return ResponseEntity.notFound().build();
    	}else {
    		produto.setIdProduto(id);
    		produtoRepository.saveAndFlush(produto);
    		
    		return ResponseEntity.noContent().build();
    	} 	    	
    };


    
    
    
    
}
