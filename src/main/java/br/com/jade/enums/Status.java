package br.com.jade.enums;

public enum Status {

	DISPONIVEL("disponível"),
	VENDIDO("vendido");
	
	private String status;
	
	private Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
	
}
