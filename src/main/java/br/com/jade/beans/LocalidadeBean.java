package br.com.jade.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jade.enums.Localidade;

@ManagedBean
@ViewScoped
public class LocalidadeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1685325345363755472L;
	private String localidade;
	private List<String> localidades;
	
	public LocalidadeBean() {
		this.localidades = new ArrayList<>();
		this.localidades.add(Localidade.LOJA.getLocalidade());
		this.localidades.add(Localidade.REVENDEDOR.getLocalidade());
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public List<String> getLocalidades() {
		return localidades;
	}
	
	
}
