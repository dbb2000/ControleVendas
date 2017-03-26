package br.com.jade.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.DragDropEvent;
import org.primefaces.event.RowEditEvent;

import br.com.jade.dao.RevendProdDao;
import br.com.jade.enums.Status;
import br.com.jade.model.Produto;
import br.com.jade.model.Revendedor;

@ManagedBean
@SessionScoped
public class RevendProdBean implements Serializable {

	private static final long serialVersionUID = 2952101094659010510L;

	private Revendedor selectedRevendedor;
	private List<Produto> produtos;	
	private List<Produto> produtosFiltrados;
	private List<Produto> produtosRevendedoresFiltrados;
	
//	@ManagedProperty("#{revendProdDao}")
	private RevendProdDao revendProdDao = new RevendProdDao();
	
    @ManagedProperty("#{modoVendaBean}")
    private ModoVendaBean modoVenda;
    
    @ManagedProperty("#{statusBean}")
    private StatusBean status;

//    @PostConstruct()
//    public void init() {
//   		
//    		 
//    }
    
    public String onLoad() {
    	produtos = revendProdDao.getProdutos();
        return "cadProdReven";        
    }
    
    public void somaProdutos(){
    	
    	BigDecimal total = new BigDecimal(0);
    	
    	for(Produto produto : selectedRevendedor.getProdutos()){
    		total = total.add(produto.getPrecoVenda());
    		
    	}    	
    	selectedRevendedor.setTotalMercadorias(total);
    }
  
    public void onRevendedorDrop(DragDropEvent ddEvent) {
        Produto produto = ((Produto) ddEvent.getData());
        this.selectedRevendedor.getProdutos().add(produto);
        this.somaProdutos();
        // preciso remover item dos produtos.
        this.produtos.remove(produto);
        
    }
    
    public void onListaProdutosDrop(DragDropEvent ddEvent) {
        Produto produto = ((Produto) ddEvent.getData());
        this.selectedRevendedor.getProdutos().remove(produto);
        this.somaProdutos();
        this.produtos.add(produto);
        revendProdDao.atualizaStatus(produto);
        
        
    }
    
	public void onRowEdit(RowEditEvent event) {
		
		Produto produto = (Produto) event.getObject();
		
		this.somaProdutos();
		
		revendProdDao.atualizar(produto, selectedRevendedor.getApelido());
		
		if(produto.getStatus().equals(Status.VENDIDO.getStatus())){
	    	revendProdDao.removerProdutoVendido(selectedRevendedor, produto);
			this.selectedRevendedor.getProdutos().remove(produto);
	    	this.somaProdutos();
	    	revendProdDao.gravar(selectedRevendedor);
		}
		
    	FacesMessage msg = new FacesMessage("Produto Editado", produto.getCodigo());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edição cancelada", ((Produto) event.getObject()).getCodigo());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String atualizarPagina(){
    	return null;
    }
    

    
    public String gravar(){
    	this.somaProdutos();
    	revendProdDao.gravar(selectedRevendedor);
    	FacesContext context = FacesContext.getCurrentInstance();
    	FacesMessage mensagem = new FacesMessage(
    	FacesMessage.SEVERITY_INFO, "Produtos atribuídos.",
    	"Produtos para o revendedor " + selectedRevendedor.getApelido() + " atribuidos com sucesso.");
    	context.addMessage(null, mensagem);
    	
    	return null;    	    	
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

	public List<Produto> getProdutosRevendedoresFiltrados() {
		return produtosRevendedoresFiltrados;
	}

	public void setProdutosRevendedoresFiltrados(List<Produto> produtosRevendedoresFiltrados) {
		this.produtosRevendedoresFiltrados = produtosRevendedoresFiltrados;
	}
    
    
    //FIXME filtro dos produtos revendedor com bug
}
