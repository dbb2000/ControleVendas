package br.com.jade.enums;

public enum Localidade {

	LOJA("loja"),
	REVENDEDOR("revendedor");
	
	private final String localidade;
	
	private Localidade(String localidade) {
		this.localidade = localidade;
	}

	public String getLocalidade() {
		return localidade;
	}
	
}
