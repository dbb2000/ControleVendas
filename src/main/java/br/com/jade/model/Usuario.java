package br.com.jade.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	@Column(name="id", nullable=false, unique=true)
	private int id;

	@Column(name="userName", nullable=false, unique=true)
	private String nomeUsuario;

	@Column(name="password", nullable=false, unique=false)
	private String senha;

	@Column(name="NOME_COMPLETO", nullable=false, unique=false)
	private String nomeCompleto;

	@Column(name="lastAccess", unique=true)
	private LocalDate ultimoAcesso;

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(LocalDate ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

}