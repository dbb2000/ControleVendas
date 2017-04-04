package br.com.jade.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PRODUTO")
public class Produto {

	@Id	
	@GeneratedValue//(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	Long id;
	
	@Column(name="CODIGO")
	private String codigo;
	
	@Column(name="DESCRICAO", length = 255)
	private String descricao;
	
	//@Enumerated(EnumType.STRING)
	@Column(name="LOCALIDADE")
	//Enum<Localidade> localidade; 
	private String localidade;
	
	@Column(name="PRECO_CUSTO", precision = 10, scale = 2)
	private BigDecimal precoCusto;
	
	@Column(name="PRECO_CUSTO_EFETIVO", precision = 10, scale = 2)
	private BigDecimal precoCustoEfetivo;
	
	@Column(name="PRECO_VENDA", precision = 10, scale = 2)
	private BigDecimal precoVenda;
	
	//@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	//Enum<Status> status;
	private String status;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ENTRADA")
	private Date dataEntrada;
	
	@OneToOne(optional=false)
	@JoinColumn(name = "COD_VENDA")
	private Venda venda;
	
	@ManyToOne()
	@JoinColumn(name = "ID_CUSTO")
	Custo custo;
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
//	public Enum<Localidade> getLocalidade() {
//		return localidade;
//	}
//	public void setLocalidade(Enum<Localidade> localidade) {
//		this.localidade = localidade;
//	}
	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}
	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}
	public BigDecimal getPrecoCustoEfetivo() {
		return precoCustoEfetivo;
	}
	public void setPrecoCustoEfetivo(BigDecimal precoCustoEfetivo) {
		this.precoCustoEfetivo = precoCustoEfetivo;
	}
	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}
//	public Enum<Status> getStatus() {
//		return status;
//	}
//	public void setStatus(Enum<Status> status) {
//		this.status = status;
//	}
	public String getLocalidade() {
		return localidade;
	}
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public Custo getCusto() {
		return custo;
	}
	public void setCusto(Custo custo) {
		this.custo = custo;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
