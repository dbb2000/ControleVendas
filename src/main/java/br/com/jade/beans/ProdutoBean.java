package br.com.jade.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import br.com.jade.dao.ProdutoDao;
import br.com.jade.model.Produto;
import br.com.jade.model.TaxaCartao;
 
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private List<Produto> produtos;	
	private List<Produto> produtosFiltrados; 
	private Produto produto = new Produto();
	private TaxaCartao taxas;
         
    private ProdutoDao produtoDao = new ProdutoDao();
    
    @ManagedProperty("#{localidadeBean}")    
    private LocalidadeBean localidade;
    
    @ManagedProperty("#{modoVendaBean}")
    private ModoVendaBean modoVenda;
    
    @ManagedProperty("#{statusBean}")
    private StatusBean status;
    
    @PostConstruct()
    public void init() {
   		produtos = produtoDao.getProdutos();
   		taxas = produtoDao.getTaxasMercadoLivre();
    }
 
    public List<Produto> getProdutos() {
        return produtos;
    }
        
	public void setProdutosFiltrados(List<Produto> produtosFiltrados) {
		this.produtosFiltrados = produtosFiltrados;
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<String> getLocalidades() {
        return localidade.getLocalidades();
    }
     
    public List<String> getModosVenda() {
        return modoVenda.getModosVenda();
    }
    
    public List<String> getTodosStatus() {
        return status.getTodosStatus();
    }
 
    public void setProdutoDao(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }
    
    public void setLocalidade(LocalidadeBean localidade) {
        this.localidade = localidade;
    }
    
    public void setModoVenda(ModoVendaBean modoVenda) {
        this.modoVenda = modoVenda;
    }
     
    public void setStatus(StatusBean status) {
        this.status = status;
    }
 
	public void onRowEdit(RowEditEvent event) {
    	produtoDao.atualizar((Produto) event.getObject(), this.taxas);
        FacesMessage msg = new FacesMessage("Produto Editado", ((Produto) event.getObject()).getCodigo());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edição cancelada", ((Produto) event.getObject()).getCodigo());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String gravar(){
    	produtoDao.gravar(produto);
    	FacesContext context = FacesContext.getCurrentInstance();
    	FacesMessage mensagem = new FacesMessage(
    	FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
    	"Produto " + produto.getCodigo() + " cadastrado com sucesso.");
    	context.addMessage(null, mensagem);
    	return "produtos";
    	    	
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Célula alterada", "Antigo: " + oldValue + ", Novo:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
   
 
}