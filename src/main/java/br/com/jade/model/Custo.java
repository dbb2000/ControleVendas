package br.com.jade.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CUSTO")
public class Custo {

	@Id
	@GeneratedValue
	@Column(name="ID")
	Long id;
	
	@Column(name="DATA_COMPRA")
	@Temporal(TemporalType.DATE)
	Date dataCompra;
	
	@Column(name="QTDE_PECAS", length = 4)
	int qtdePecas;
	
	@Column(name="DESCONTO_RECEBIDO")	
	Long descontoRecebido;
	
	@Column(name="COMBUSTIVEL")
	BigDecimal combustivel;
	
	@Column(name="PEDAGIO")
	BigDecimal pedagio;
	
	@Column(name="FRETE")
	BigDecimal frete;
	
	@Column(name="OUTROS")
	BigDecimal outros;
	
	@Column(name="CUSTO_TOTAL")
	BigDecimal custoTotal;
	
	@Column(name="ADICIONAL_PECA")
	Long adicionalPeca;


	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public int getQtdePecas() {
		return qtdePecas;
	}

	public void setQtdePecas(int qtdePecas) {
		this.qtdePecas = qtdePecas;
	}

	public Long getDescontoRecebido() {
		return descontoRecebido;
	}

	public void setDescontoRecebido(Long descontoRecebido) {
		this.descontoRecebido = descontoRecebido;
	}

	public BigDecimal getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(BigDecimal combustivel) {
		this.combustivel = combustivel;
	}

	public BigDecimal getPedagio() {
		return pedagio;
	}

	public void setPedagio(BigDecimal pedagio) {
		this.pedagio = pedagio;
	}

	public BigDecimal getFrete() {
		return frete;
	}

	public void setFrete(BigDecimal frete) {
		this.frete = frete;
	}

	public BigDecimal getOutros() {
		return outros;
	}

	public void setOutros(BigDecimal outros) {
		this.outros = outros;
	}

	public BigDecimal getCustoTotal() {
		return custoTotal;
	}

	public void setCustoTotal(BigDecimal custoTotal) {
		this.custoTotal = custoTotal;
	}

	public Long getAdicionalPeca() {
		return adicionalPeca;
	}

	public void setAdicionalPeca(Long adicionalPeca) {
		this.adicionalPeca = adicionalPeca;
	}
	
	


	
	
	
	
}
