package br.com.jade.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.jade.model.Custo;
import br.com.jade.model.Revendedor;

@ManagedBean
@ViewScoped
public class RevendedorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1652594235516983389L;

    @ManagedProperty("#{revendedor}")
    private Revendedor revendedor;

	@ManagedProperty("#{revendedorDao}")
	private revendedorDao revendedorDao;

    @PostConstruct
    public void init() {
        revendedor = new Revendedor();
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
    
	public Revendedor getRevendedor() {
		return revendedor;
	}

	public void setRevendedor(Revendedor revendedor) {
		this.revendedor = revendedor;
	}

	public revendedorDao getRevendedorDao() {
		return revendedorDao;
	}

	public void setRevendedorDao(revendedorDao revendedorDao) {
		this.revendedorDao = revendedorDao;
	}
	
	
}
