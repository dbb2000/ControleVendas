package br.com.jade.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "REVENDEDOR")
public class Revendedor {

//	@Id
//	@GeneratedValue
//	@Column(name="ID")
//	int id;
	
	@Id
	@Column(name="APELIDO", unique=true, columnDefinition="VARCHAR(15)")
	String apelido;
	
	@Column(name="NOME_COMPLETO")
	String nomeCompleto;
	
	@Column(name="ENDERECO")	
	String endereço;
	
	@Column(name="CPF")
	String cpf;
	
	@Column(name="RG")
	String rg;
	
	@Column(name="COMPLEMENTO")
	String complemento;
	
	@Column(name="BAIRRO")
	String bairro;
	
	@Column(name="CIDADE")
	String cidade;

	@Column(name="UF", length=2 )
	String uf;

	@Column(name="CEP")
	String cep;
	
	@Column(name="FONE_RESIDENCIAL")
	String foneResidencial;
	
	@Column(name="FONE_CELULAR")
	String foneCelular;
	
	@OneToMany
	List<Produto> produtos;
	
	@Column(name="TOTAL_MERCADORIAS")
	BigDecimal totalMercadorias;
	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getEndereço() {
		return endereço;
	}
	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getFoneResidencial() {
		return foneResidencial;
	}
	public void setFoneResidencial(String foneResidencial) {
		this.foneResidencial = foneResidencial;
	}
	public String getFoneCelular() {
		return foneCelular;
	}
	public void setFoneCelular(String foneCelular) {
		this.foneCelular = foneCelular;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public BigDecimal getTotalMercadorias() {
		return totalMercadorias;
	}
	public void setTotalMercadorias(BigDecimal totalMercadorias) {
		this.totalMercadorias = totalMercadorias;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	//	public int getId() {
//		return id;
//	}
//	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apelido == null) ? 0 : apelido.hashCode());
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
		Revendedor other = (Revendedor) obj;
		if (apelido == null) {
			if (other.apelido != null)
				return false;
		} else if (!apelido.equals(other.apelido))
			return false;
		return true;
	}
	
	
	
	
}
