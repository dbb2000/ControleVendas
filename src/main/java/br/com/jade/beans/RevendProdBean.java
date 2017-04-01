package br.com.jade.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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
	private List<Produto> produtosRevendedorFiltrados;
	
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
    	this.produtos = null;
    	this.selectedRevendedor.getProdutos().clear();
    	
    	this.produtos = revendProdDao.getProdutos();
    	this.selectedRevendedor.setProdutos(revendProdDao.buscaLista(selectedRevendedor.getApelido()));
    	
    	
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
  
        // preciso remover item dos produtos.
        this.produtos.remove(produto);
        if( produtosFiltrados != null ){
        	produtosFiltrados.remove(produto);
        	produtosFiltrados = null;

        }
        
        if(produtosRevendedorFiltrados != null){
        	produtosRevendedorFiltrados = null;

        }
        
        this.selectedRevendedor.getProdutos().add(produto);
        this.somaProdutos();
        
    }
    
    public void onListaProdutosDrop(DragDropEvent ddEvent) {
        Produto produto = ((Produto) ddEvent.getData());
        this.selectedRevendedor.getProdutos().remove(produto);
        if(produtosRevendedorFiltrados != null){
        	produtosRevendedorFiltrados.remove(produto);
        	produtosRevendedorFiltrados = null;

        }
        
        if( produtosFiltrados != null ){
        	produtosFiltrados = null;

        }
        
        this.produtos.add(produto);
        revendProdDao.atualizaStatus(produto);
        this.somaProdutos();
        
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

	public List<Produto> getProdutosRevendedorFiltrados() {
		return produtosRevendedorFiltrados;
	}

	public void setProdutosRevendedorFiltrados(List<Produto> produtosRevendedorFiltrados) {
		this.produtosRevendedorFiltrados = produtosRevendedorFiltrados;
	}
    
    //FIXME adicionar o tratamento para produtosfiltados. deve sumir da tabela de disponíveis quando um filtro é usado
	//FIXME fazer o mesmo no filtro de produtos do revendedor
	//TODO adicionar codigo e descrição do produto como itens filtrados na tabela de disponíveis e revendedor
	//TODO verificar a questão do @Inject
	//TODO reimplementar o login. Pag 200 da apostila
}
