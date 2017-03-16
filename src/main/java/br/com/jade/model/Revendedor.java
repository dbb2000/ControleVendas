package br.com.jade.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "REVENDEDOR")
public class Revendedor {

	@Id
	@GeneratedValue
	@Column(name="ID")
	int id;
	

	@Column(name="APELIDO")
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
	
	
	
	
	
}
