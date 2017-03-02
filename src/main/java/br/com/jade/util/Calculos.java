package br.com.jade.util;

import java.math.BigDecimal;

public class Calculos {

	public static BigDecimal calculaCustoEfetivo(int pecas, Long desconto, BigDecimal precoCusto){
		
		
		
		BigDecimal valor  = (BigDecimal.valueOf(desconto).divide(precoCusto)) ;
		
		
		return null;
		
	}
	
	public static BigDecimal calculaPrecoVenda(Long margemLucro, BigDecimal custoEfetivo){
		return null;
		
	}
}
