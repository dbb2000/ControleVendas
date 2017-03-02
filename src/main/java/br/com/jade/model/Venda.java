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
@Table(name = "VENDA")
public class Venda {

	@Id
	@GeneratedValue//(strategy=GenerationType.IDENTITY)
	@Column(name="COD_VENDA")
	Long codigoVenda;	
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_VENDA")
	Date dataVenda;	
	
	@Column(name="VALOR_VENDA")
	BigDecimal precoVenda;	
	
	//@Enumerated(EnumType.STRING)
	@Column(name="FORMA_PGTO")
	//Enum<ModoVenda> formaPagamento;
	String formaPagamento;

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public Long getCodigoVenda() {
		return codigoVenda;
	}

	public void setCodigoVenda(Long codigoVenda) {
		this.codigoVenda = codigoVenda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoVenda == null) ? 0 : codigoVenda.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		if (codigoVenda == null) {
			if (other.codigoVenda != null)
				return false;
		} else if (!codigoVenda.equals(other.codigoVenda))
			return false;
		return true;
	}
	
	
	

	
	
	
}
