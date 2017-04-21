package br.com.jade.beans;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.RowEditEvent;

import br.com.jade.dao.RevendProdDao;
import br.com.jade.enums.Status;
import br.com.jade.model.Produto;
import br.com.jade.model.Revendedor;
import br.com.jade.report.GeradorDeRelatorios;
import net.sf.jasperreports.engine.JRException;

@ManagedBean
@SessionScoped
public class RevendProdBean implements Serializable {

	private static final long serialVersionUID = 2952101094659010510L;

	private Revendedor selectedRevendedor;
	private List<Produto> produtos;	
	private List<Produto> produtosFiltrados;
	private List<Produto> produtosRevendedorFiltrados;
	private RevendProdDao revendProdDao = new RevendProdDao();
	
	private final String LOGOTIPO = "resources/images/logo.png";
	private final String JRXML = "resources/jasper/report1.jrxml";
	
    @ManagedProperty("#{modoVendaBean}")
    private ModoVendaBean modoVenda;
    
    @ManagedProperty("#{statusBean}")
    private StatusBean status;

    
    public String onLoad() {
    	this.produtos = null;
    	this.selectedRevendedor.setProdutos(null);
    	this.limpaWidgetVar();    	
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
        }
    	
        this.limpaWidgetVar();
        this.selectedRevendedor.getProdutos().add(produto);
        this.somaProdutos();
    }
    
    public void onListaProdutosDrop(DragDropEvent ddEvent) {

    	Produto produto = ((Produto) ddEvent.getData());
        this.selectedRevendedor.getProdutos().remove(produto);
        
        if(produtosRevendedorFiltrados != null){
        	produtosRevendedorFiltrados.remove(produto);
        }
        
        revendProdDao.atualizaStatus(produto);
        this.produtos.add(produto);        
        this.somaProdutos();        
    }
    
	public void onRowEdit(RowEditEvent event) {
		
		Produto produto = (Produto) event.getObject();
		revendProdDao.atualizar(produto, selectedRevendedor.getApelido());
    	this.somaProdutos();
		
    	FacesMessage msg = new FacesMessage("Produto Atualizado com sucesso", produto.getCodigo());
        FacesContext.getCurrentInstance().addMessage(null, msg);        
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edição cancelada", ((Produto) event.getObject()).getCodigo());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void gravar(){
    	this.removerProdutosVendidos();
    	this.somaProdutos();
    	revendProdDao.gravar(selectedRevendedor);
    	
    	this.limpaWidgetVar();
    	    	
    	FacesContext context = FacesContext.getCurrentInstance();
    	FacesMessage mensagem = new FacesMessage(
    	FacesMessage.SEVERITY_INFO, "Produtos atribuídos.",
    	"Produtos para o revendedor " + selectedRevendedor.getApelido() + " modificado com sucesso.");
    	context.addMessage(null, mensagem);    	    	
    }     

	private void removerProdutosVendidos() {
		
		List<Produto> listaDisponiveis = new ArrayList<>();
		
		for(Produto produto : selectedRevendedor.getProdutos()){
			
			if(Status.DISPONIVEL.getStatus().equalsIgnoreCase(produto.getStatus())){
				listaDisponiveis.add(produto);
			}
		}		
		selectedRevendedor.getProdutos().clear();
		selectedRevendedor.setProdutos(listaDisponiveis);		
	}
	
	/**
	 * limpa as variáveis widgetvar da tela quando o filtro de pesquisa é aplicado
	 */
	private void limpaWidgetVar(){
		RequestContext requestContext = RequestContext.getCurrentInstance();
   	 	requestContext.execute("PF('produtosTable').clearFilters()");
   	 	requestContext.execute("PF('produtosRevendTable').clearFilters()");
	}
	
	public void imprimeRelatorio() throws ClassNotFoundException, SQLException, JRException, IOException{

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		

		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();		
		Connection conexao = revendProdDao.getConnection();		
		Map<String, Object> parametros = new HashMap<>();
		
		String absoluteLogoPath= externalContext.getRealPath(LOGOTIPO); //caminho do logo que compõe o relatório
		String absolutePath= externalContext.getRealPath(JRXML); // caminho para o jrxml do relatório
				
		parametros.put("revendedor", selectedRevendedor.getApelido());
		parametros.put("logo", absoluteLogoPath);

		GeradorDeRelatorios gerador = new GeradorDeRelatorios(conexao);
		gerador.geraPdf(parametros, response, absolutePath);
		conexao.close();

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
    

	//TODO verificar a questão do @Inject
	//TODO reimplementar o login. Pag 200 da apostila
	//TODO colocar verificação nos campos obrigatórios
	//TODO verificar se o apelido já existe ao cadastrar novo revendedor
}
