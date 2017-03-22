package br.com.jade.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAXA_CARTAO")
public class TaxaCartao {

	//TODO precisa calcular o desconto de taxas do cartão se a venda for cred ou deb
	@Id
	@GeneratedValue
	@Column(name="ID")
	Long id;
	
	@Column(name="TAXA_DEBITO")	
	Long taxaDebito;
	
	@Column(name="TAXA_CREDITO")
	Long taxaCredito;
	
	
	public Long getTaxaDebito() {
		return taxaDebito;
	}
	public void setTaxaDebito(Long taxaDebito) {
		this.taxaDebito = taxaDebito;
	}
	public Long getTaxaCredito() {
		return taxaCredito;
	}
	public void setTaxaCredito(Long taxaCredito) {
		this.taxaCredito = taxaCredito;
	}
	
	
}
