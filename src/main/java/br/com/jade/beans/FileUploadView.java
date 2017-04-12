package br.com.jade.beans;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.io.IOUtils;
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

//	@ManagedProperty("#{produtoDao}")
    private ProdutoDao produtoDao = new ProdutoDao();
	
//	@ManagedProperty("#{custoDao}")
	private CustoDao custoDao = new CustoDao();
    
    @ManagedProperty("#{custo}")
    private Custo custo;
    
    @ManagedProperty("#{margemLucro}")
    private BigDecimal margemLucro;


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
    	
		// aqui crio duas cópias de InputStream, uma para contar linha e outra para iterar
		// pois não posso usar o mesmo InputStream duas vezes.
		byte[] byteArray = IOUtils.toByteArray(event.getFile().getInputstream()); 
		InputStream input1 = new ByteArrayInputStream(byteArray);
	    InputStream input2 = new ByteArrayInputStream(byteArray);
    	
	    int totalLinhas = contarLinhas(input1);
    	custoDao.atualizarQtdePecas(custo, totalLinhas);
    	produtoDao.importar(input2, this.custo, this.margemLucro, totalLinhas);
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

	public BigDecimal getMargemLucro() {
		return margemLucro;
	}

	public void setMargemLucro(BigDecimal margemLucro) {
		this.margemLucro = margemLucro;
	}
	
	public void setCustoDao(CustoDao custoDao) {
		this.custoDao = custoDao;
	}

	public void margemChanged(ValueChangeEvent e){

		this.margemLucro = (BigDecimal) e.getNewValue(); 
	}

	public void outrosChanged(ValueChangeEvent e){

		this.custo.setOutros( (BigDecimal) e.getNewValue());
		somar();
	}

	public void descontoChanged(ValueChangeEvent e){

		this.custo.setDescontoRecebido((BigDecimal) e.getNewValue());   
	}

	public void dataCompraChanged(ValueChangeEvent e){

		this.custo.setDataCompra((Date) e.getNewValue());   
	}

	public void combustivelChanged(ValueChangeEvent e){

		this.custo.setCombustivel( (BigDecimal) e.getNewValue());
		somar();
	}


	public void pedagioChanged(ValueChangeEvent e){

		this.custo.setPedagio((BigDecimal) e.getNewValue());
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
    
	private int contarLinhas (InputStream file) throws IOException{
		
		BufferedReader br = null;
		String line;
		int numberOfLines = 0;

		try {
			
			br = new BufferedReader(new InputStreamReader(file));
			while ((line = br.readLine()) != null) {
				
				String[] campos = line.split(";");				
				int qtde = Integer.parseInt(campos[2]);				
				numberOfLines = numberOfLines + qtde;				
			}
		}
		catch (Exception e){ 
			System.out.println(e); 
		}
		return numberOfLines;
	}
}





















