package br.com.jade.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class DadosEstatiscosBean {

	
	private String mesInicial;
	private String mesFinal;
	
	
	
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
	
	
}
