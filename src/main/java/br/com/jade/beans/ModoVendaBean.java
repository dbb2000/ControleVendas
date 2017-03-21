package br.com.jade.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.jade.enums.ModoVenda;

@ManagedBean
@SessionScoped
public class ModoVendaBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7794009341381998135L;
	private String modoVenda;
	private List<String> modosVenda;
	
	public ModoVendaBean() {
		this.modosVenda = new ArrayList<>();
		this.modosVenda.add(ModoVenda.NENHUM.getModoVenda());
		this.modosVenda.add(ModoVenda.CREDITO.getModoVenda());
		this.modosVenda.add(ModoVenda.DEBITO.getModoVenda());
		this.modosVenda.add(ModoVenda.DINHEIRO.getModoVenda());
	}

	public String getModoVenda() {
		return modoVenda;
	}

	public void setModoVenda(String modoVenda) {
		this.modoVenda = modoVenda;
	}

	public List<String> getModosVenda() {
		return modosVenda;
	}
	
	
}
