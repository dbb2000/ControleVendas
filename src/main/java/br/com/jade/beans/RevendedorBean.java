package br.com.jade.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import br.com.jade.model.Revendedor;

@ManagedBean
@ViewScoped
public class RevendedorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1652594235516983389L;

	private List<Revendedor> revendedores;
	private List<Revendedor> revendedoresFiltrados;
	private Revendedor revendedor = new Revendedor();
	private Revendedor selectedRevendedor;
	
//    @ManagedProperty("#{revendedor}")
//    private Revendedor revendedor;

	@ManagedProperty("#{revendedorDao}")
	private RevendedorDao revendedorDao;

    @PostConstruct
    public void init() {

        this.revendedores = revendedorDao.getRevendedores(); 
        
    }
    
    public String gravarCadastro(){ 
    	revendedorDao.gravar(revendedor);
    	FacesContext context = FacesContext.getCurrentInstance();
    	FacesMessage mensagem = new FacesMessage(
    	FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
    	"Revedendor " + revendedor.getApelido() + " cadastrado com sucesso.");
    	context.addMessage(null, mensagem);
    	return null;
    	    	
    }
    
    public String carregarCadastro(){
    	this.revendedor = selectedRevendedor;
    	return null;
    }
    
    public String excluirCadastro(){
		return null;
    	
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
