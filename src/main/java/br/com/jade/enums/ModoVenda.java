package br.com.jade.enums;

public enum ModoVenda {
	
	NENHUM(""),
	DINHEIRO("dinheiro"), 
	DEBITO("débito"),
	CREDITO("crédito");
	
	private String modoVenda;
	
	private ModoVenda(String modoVenda) {
		this.modoVenda = modoVenda;
	}

	public String getModoVenda() {
		return modoVenda;
	}	
	
}
