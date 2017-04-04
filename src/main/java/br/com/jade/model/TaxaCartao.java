package br.com.jade.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAXA_CARTAO")
public class TaxaCartao {

	//TODO precisa calcular o desconto de taxas do cartão se a venda for cred ou deb
	@Id
	@Column(name="OPERADORA", unique=true, columnDefinition="VARCHAR(15)")
	String operadora;
	
	@Column(name="TAXA_DEBITO")	
	BigDecimal taxaDebito;
	
	@Column(name="TAXA_CREDITO")
	BigDecimal taxaCredito;
	
	
	public BigDecimal getTaxaDebito() {
		return taxaDebito;
	}
	public void setTaxaDebito(BigDecimal taxaDebito) {
		this.taxaDebito = taxaDebito;
	}
	public BigDecimal getTaxaCredito() {
		return taxaCredito;
	}
	public void setTaxaCredito(BigDecimal taxaCredito) {
		this.taxaCredito = taxaCredito;
	}
	public String getOperadora() {
		return operadora;
	}
	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}
	
	
}
