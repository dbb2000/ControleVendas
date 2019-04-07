package br.com.jade.beans;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jade.dao.EstatisticoDao;
import br.com.jade.util.Calculos;

@ManagedBean
@ViewScoped
public class DadosEstatiscosBean {

	
	private String mesInicial;
	private String mesFinal;
	
	private int totalPecasDisponiveis;
	private int totalPecasLoja;
	private int totalPecasRevendedor;
	private BigDecimal custoTotalMercadorias;
	private BigDecimal valorMercadoriasAvaliado;
	private BigDecimal percentualLucro;
	private EstatisticoDao estatisticoDao = new EstatisticoDao();
	
	@PostConstruct
	public void init(){
		this.totalPecasDisponiveis = estatisticoDao.getPecasDisponiveis(); 
		this.totalPecasLoja = estatisticoDao.getPecasLoja();
		this.totalPecasRevendedor = estatisticoDao.getPecasRevendedor();
		this.custoTotalMercadorias = estatisticoDao.getCustoTotalMercadorias();
		this.valorMercadoriasAvaliado = estatisticoDao.getCustoMercadoriasAvaliado();
		this.percentualLucro = Calculos.calculaPercentualLucro(this.custoTotalMercadorias, this.valorMercadoriasAvaliado);
	}
	
	public String getMesInicial() {
		return mesInicial;
	}
	public void setMesInicial(String mesInicial) {
		this.mesInicial = mesInicial;
	}
	public String getMesFinal() {
		return mesFinal;
	}
	public void setMesFinal(String mesFinal) {
		this.mesFinal = mesFinal;
	}
	public int getTotalPecasDisponiveis() {
		return totalPecasDisponiveis;
	}
	public void setTotalPecasDisponiveis(int totalPecasDisponiveis) {
		this.totalPecasDisponiveis = totalPecasDisponiveis;
	}
	public int getTotalPecasLoja() {
		return totalPecasLoja;
	}
	public void setTotalPecasLoja(int totalPecasLoja) {
		this.totalPecasLoja = totalPecasLoja;
	}
	public int getTotalPecasRevendedor() {
		return totalPecasRevendedor;
	}
	public void setTotalPecasRevendedor(int totalPecasRevendedor) {
		this.totalPecasRevendedor = totalPecasRevendedor;
	}
	public BigDecimal getCustoTotalMercadorias() {
		return custoTotalMercadorias;
	}
	public void setCustoTotalMercadorias(BigDecimal custoTotalMercadorias) {
		this.custoTotalMercadorias = custoTotalMercadorias;
	}

	public BigDecimal getValorMercadoriasAvaliado() {
		return valorMercadoriasAvaliado;
	}

	public void setValorMercadoriasAvaliado(BigDecimal valorMercadoriasAvaliado) {
		this.valorMercadoriasAvaliado = valorMercadoriasAvaliado;
	}

	public BigDecimal getPercentualLucro() {
		return percentualLucro;
	}
	public void setPercentualLucro(BigDecimal percentualLucro) {
		this.percentualLucro = percentualLucro;
	}

	public EstatisticoDao getEstatisticoDao() {
		return estatisticoDao;
	}

	public void setEstatisticoDao(EstatisticoDao estatisticoDao) {
		this.estatisticoDao = estatisticoDao;
	}
	
	
}
