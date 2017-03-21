package br.com.jade.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.DragDropEvent;

import br.com.jade.dao.RevendProdDao;
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
	
	@ManagedProperty("#{revendProdDao}")
	private RevendProdDao revendProdDao;
	
//    @ManagedProperty("#{produtoDao}")
//    private ProdutoDao produtoDao;
	

    public String onLoad() {

        this.produtos = revendProdDao.getProdutos();
        return "cadProdReven";
        
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

	public RevendProdDao getRevendProdDao() {
		return revendProdDao;
	}

	public void setRevendProdDao(RevendProdDao revendProdDao) {
		this.revendProdDao = revendProdDao;
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public void setProdutosFiltrados(List<Produto> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}
	  
	
	  
}
