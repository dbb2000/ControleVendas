package br.com.jade.beans;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.FileUploadEvent;

import br.com.jade.dao.CustoDao;
import br.com.jade.dao.ProdutoDao;
import br.com.jade.model.Custo;
 
@ManagedBean
@ViewScoped
public class FileUploadView implements Serializable {
 
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5740654576960452880L;

	@ManagedProperty("#{produtoDao}")
    private ProdutoDao produtoDao;
	
	@ManagedProperty("#{custoDao}")
	private CustoDao custoDao;
    
    @ManagedProperty("#{custo}")
    private Custo custo;
    
    @ManagedProperty("#{margemLucro}")
    private Long margemLucro;


    @PostConstruct
    public void init() {
        custo = new Custo();
        custo.setCustoTotal(new BigDecimal(0));
        custo.setCombustivel(new BigDecimal(0));
        custo.setFrete(new BigDecimal(0));
        custo.setOutros(new BigDecimal(0));
        custo.setPedagio(new BigDecimal(0));
    }
	
    public void handleFileUpload(FileUploadEvent event) throws IOException {
    	produtoDao.importar(event.getFile().getInputstream(), this.custo, this.margemLucro);
        FacesMessage message = new FacesMessage("Sucesso", event.getFile().getFileName() + " foi importado com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

	public ProdutoDao getProdutoDao() {
		return produtoDao;
	}

	public void setProdutoDao(ProdutoDao produtoDao) {
		this.produtoDao = produtoDao;
	}

	public Custo getCusto() {
		return custo;
	}

	public void setCusto(Custo custo) {
		this.custo = custo;
	}

	public Long getMargemLucro() {
		return margemLucro;
	}

	public void setMargemLucro(Long margemLucro) {
		this.margemLucro = margemLucro;
	}
	
	public void setCustoDao(CustoDao custoDao) {
		this.custoDao = custoDao;
	}

	public void margemChanged(ValueChangeEvent e){

		   this.margemLucro = (Long) e.getNewValue(); 
	}
	
	public void outrosChanged(ValueChangeEvent e){

		   this.custo.setOutros( (BigDecimal) e.getNewValue());
		   somar();
	}
	
	public void descontoChanged(ValueChangeEvent e){

		   this.custo.setDescontoRecebido((Long) e.getNewValue());   
	}
	
	public void dataCompraChanged(ValueChangeEvent e){

		   this.custo.setDataCompra((Date) e.getNewValue());   
	}

	public void combustivelChanged(ValueChangeEvent e){

		   this.custo.setCombustivel( (BigDecimal) e.getNewValue());
//		   custo.setCustoTotal(custo.getCustoTotal().add(custo.getCombustivel()));
		   somar();
	}
	

	public void pedagioChanged(ValueChangeEvent e){

		   this.custo.setPedagio((BigDecimal) e.getNewValue());
//		   custo.setCustoTotal(custo.getCustoTotal().add(custo.getPedagio()));
		   somar();
	}
	

	public void freteChanged(ValueChangeEvent e){

		   this.custo.setFrete((BigDecimal) e.getNewValue());
		   somar();
	}
    
	public void somar() {
		
		BigDecimal resultado = custo.getCombustivel().add(custo.getPedagio()).add(custo.getFrete()).add(custo.getOutros());
		
		this.custo.setCustoTotal(resultado);
		}
	
	
    public String gravarCusto(){
    	custoDao.gravar(custo);
    	FacesContext context = FacesContext.getCurrentInstance();
    	FacesMessage mensagem = new FacesMessage(
    	FacesMessage.SEVERITY_INFO, "Cadastro efetuado.",
    	"Custo " + custo.getId() + " cadastrado com sucesso.");
    	context.addMessage(null, mensagem);
    	return null;
    	    	
    }
}





















