package br.com.jade.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jade.enums.Status;

@ManagedBean
@ViewScoped
public class StatusBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 688290062202132000L;
	private String status;
	private List<String> todosStatus;
	
	public StatusBean() {
		this.todosStatus = new ArrayList<>();
		this.todosStatus.add(Status.DISPONIVEL.getStatus());
		this.todosStatus.add(Status.VENDIDO.getStatus());
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getTodosStatus() {
		return todosStatus;
	}
	
	
}
