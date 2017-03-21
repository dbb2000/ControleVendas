package br.com.jade.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.DragDropEvent;
import org.primefaces.event.RowEditEvent;

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
	
    @ManagedProperty("#{modoVendaBean}")
    private ModoVendaBean modoVenda;
    
    @ManagedProperty("#{statusBean}")
    private StatusBean status;
	
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
    
	public void onRowEdit(RowEditEvent event) {
    	revendProdDao.atualizar((Produto) event.getObject());
        FacesMessage msg = new FacesMessage("Produto Editado", ((Produto) event.getObject()).getCodigo());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edição cancelada", ((Produto) event.getObject()).getCodigo());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String gravar(){
//    	revendProdDao.gravar(produto);
//    	FacesContext context = FacesContext.getCurrentInstance();
//    	FacesMessage mensagem = new FacesMessage(
//    	FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
//    	"Produto " + produto.getCodigo() + " cadastrado com sucesso.");
//    	context.addMessage(null, mensagem);
    	return "produtos";
    	    	
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

	public ModoVendaBean getModoVenda() {
		return modoVenda;
	}

	public void setModoVenda(ModoVendaBean modoVenda) {
		this.modoVenda = modoVenda;
	}

	public StatusBean getStatus() {
		return status;
	}

	public void setStatus(StatusBean status) {
		this.status = status;
	}
	
    public List<String> getModosVenda() {
        return modoVenda.getModosVenda();
    }
    
    public List<String> getTodosStatus() {
        return status.getTodosStatus();
    }
}
