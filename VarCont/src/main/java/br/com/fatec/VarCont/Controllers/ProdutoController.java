/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.VarCont.Controllers;

import br.com.fatec.VarCont.DataSource.Models.Produto;
import br.com.fatec.VarCont.services.BuscarProdutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping ("/admin")
public class ProdutoController {
    
      
    @Autowired
    private BuscarProdutoService buscarProdutoService;
    
    @GetMapping (path = "/produto")
    public List<Produto> buscarProduto()
    {
        return buscarProdutoService.BuscarProduto();
    }
    
    
    
}
