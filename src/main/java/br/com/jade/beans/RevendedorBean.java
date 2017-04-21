package br.com.jade.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.jade.dao.RevendedorDao;
import br.com.jade.model.Revendedor;

@ManagedBean
@RequestScoped
public class RevendedorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1652594235516983389L;

	private List<Revendedor> revendedores;
	private List<Revendedor> revendedoresFiltrados;
	private Revendedor revendedor = new Revendedor();
	private Revendedor selectedRevendedor;
	private RevendedorDao revendedorDao = new RevendedorDao();

    @PostConstruct
    public void init() {

        this.revendedores = revendedorDao.getRevendedores();         
    }
    
    public String gravarCadastro(){

    	//caso seja uma alteração cadastral do revendedeor, preciso recuperar a lista de produtos e o valor total de mercadorias
    	if(revendedor.getProdutos() == null){
    		for( Revendedor revend : revendedores ){
    			if(revend.getApelido().equals(this.revendedor.getApelido())){
    				this.revendedor.setProdutos(revend.getProdutos());
    				this.revendedor.setTotalMercadorias(revend.getTotalMercadorias());
    			}
    		}
    	}
    	
    	revendedorDao.gravar(revendedor);
    	FacesContext context = FacesContext.getCurrentInstance();
    	FacesMessage mensagem = new FacesMessage(
    	FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
    	"Revedendor " + revendedor.getApelido() + " cadastrado com sucesso.");
    	context.addMessage(null, mensagem);
    	
    	return "revendedores?faces-redirect=true";    	    	
    }
    
    public String carregarCadastro(){
    	this.revendedor = selectedRevendedor;
    	return "cadRevendedor";
    }
    
    public String excluirCadastro(){
		
    	FacesContext context = FacesContext.getCurrentInstance();
    	
    	if(selectedRevendedor.getProdutos().isEmpty()){
    		
    		revendedorDao.excluir(selectedRevendedor);    	
        	FacesMessage mensagem = new FacesMessage(
        	FacesMessage.SEVERITY_INFO, "Cadastro removido.",
        	"Revedendor " + selectedRevendedor.getApelido() + " removido com sucesso.");
        	context.addMessage(null, mensagem);
        	return "revendedores?faces-redirect=true";
    	} else {
			
        	FacesMessage mensagem = new FacesMessage(
        	FacesMessage.SEVERITY_INFO, "Atenção.",
        	"Não é possível remover revendedor com produtos atribuídos");
        	context.addMessage(null, mensagem);
    		return null;
		}
    	
    }
    
    
	public Revendedor getRevendedor() {
		return revendedor;
	}

	public void setRevendedor(Revendedor revendedor) {
		this.revendedor = revendedor;
	}

	public RevendedorDao getRevendedorDao() {
		return revendedorDao;
	}

	public void setRevendedorDao(RevendedorDao revendedorDao) {
		this.revendedorDao = revendedorDao;
	}

	public List<Revendedor> getRevendedores() {
		return revendedores;
	}

	public void setRevendedores(List<Revendedor> revendedores) {
		this.revendedores = revendedores;
	}

	public List<Revendedor> getRevendedoresFiltrados() {
		return revendedoresFiltrados;
	}

	public void setRevendedoresFiltrados(List<Revendedor> revendedoresFiltrados) {
		this.revendedoresFiltrados = revendedoresFiltrados;
	}

	public Revendedor getSelectedRevendedor() {
		return selectedRevendedor;
	}

	public void setSelectedRevendedor(Revendedor selectedRevendedor) {
		this.selectedRevendedor = selectedRevendedor;
	}
}
