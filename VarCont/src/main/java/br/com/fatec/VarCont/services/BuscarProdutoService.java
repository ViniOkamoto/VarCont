package br.com.fatec.VarCont.services;

import br.com.fatec.VarCont.DataSource.Models.Produto;
import br.com.fatec.VarCont.Repository.ProdutoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BuscarProdutoService {
    
    private static final Logger LOG = Logger
            .getLogger(BuscarProdutoService.class);
            
    @Autowired
    private ProdutoRepository produtoRepository;
    
    public List<Produto> BuscarProduto()
    {
        LOG.info ("Serviço para buscar os Produtos sendo executado");
        List<Produto> listaProdutos = produtoRepository.findAll();
        return listaProdutos;
    }
    
}
