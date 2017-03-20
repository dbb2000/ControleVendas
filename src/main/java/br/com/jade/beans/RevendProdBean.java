package br.com.jade.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.DragDropEvent;

import br.com.jade.dao.ProdutoDao;
import br.com.jade.dao.RevendedorDao;
import br.com.jade.model.Produto;
import br.com.jade.model.Revendedor;

@ManagedBean
@SessionScoped
public class RevendProdBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2952101094659010510L;

	private Revendedor selectedRevendedor;
	private List<Produto> produtos;	
	private List<Produto> produtosFiltrados; 
	
	@ManagedProperty("#{revendedorDao}")
	private RevendedorDao revendedorDao;
	
    @ManagedProperty("#{produtoDao}")
    private ProdutoDao produtoDao;
	
    @PostConstruct
    public void init() {

        this.produtos = produtoDao.getProdutos();
        
    }
  
    public void onRevendedorDrop(DragDropEvent ddEvent) {
        Produto produto = ((Produto) ddEvent.getData());
  
        this.selectedRevendedor.getProdutos().add(produto);
        // preciso remover item dos produtos.
        this.produtos.remove(produto);
    }
    
    public void onListaProdutosDrop(DragDropEvent ddEvent) {
        Produto produto = ((Produto) ddEvent.getData());
  
        this.selectedRevendedor.getProdutos().remove(produto);
        this.produtos.add(produto);
        
    }

	public Revendedor getSelectedRevendedor() {
		return selectedRevendedor;
	}

	public void setSelectedRevendedor(Revendedor selectedRevendedor) {
		this.selectedRevendedor = selectedRevendedor;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public RevendedorDao getRevendedorDao() {
		return revendedorDao;
	}

	public void setRevendedorDao(RevendedorDao revendedorDao) {
		this.revendedorDao = revendedorDao;
	}

	public ProdutoDao getProdutoDao() {
		return produtoDao;
	}

	public void setProdutoDao(ProdutoDao produtoDao) {
		this.produtoDao = produtoDao;
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public void setProdutosFiltrados(List<Produto> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}
	  
	
	  
}
