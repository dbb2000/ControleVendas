package br.com.jade.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculos {

	private static final BigDecimal NUMERO_CEM = new BigDecimal(100);
	
	public static BigDecimal calculaCustoEfetivo(int pecas, BigDecimal desconto, BigDecimal precoCusto, BigDecimal custoTotal){
		
		BigDecimal custoEfetivo = null;
		BigDecimal custoPecaAdcional = custoTotal.divide(BigDecimal.valueOf(pecas), 2, RoundingMode.HALF_UP);
		
		if(desconto != null || desconto != BigDecimal.valueOf(0)){
			BigDecimal descontoDecimal  = desconto.divide(NUMERO_CEM,2, RoundingMode.HALF_UP );
			BigDecimal valorDesconto = descontoDecimal.multiply(precoCusto);
			BigDecimal precoComDesconto = precoCusto.subtract(valorDesconto);
			custoEfetivo = custoPecaAdcional.add(precoComDesconto);
			return custoEfetivo.setScale(2, RoundingMode.HALF_UP);
		}
		
		custoEfetivo = precoCusto.add(custoPecaAdcional);
		return custoEfetivo.setScale(2, RoundingMode.HALF_UP);		
	}
	
	public static BigDecimal calculaPrecoVenda(BigDecimal margemLucro, BigDecimal custoEfetivo){
				
		BigDecimal margemDecimal = margemLucro.divide(NUMERO_CEM, 2, RoundingMode.HALF_UP);
		BigDecimal lucro = margemDecimal.multiply(custoEfetivo);
		
		return custoEfetivo.add(lucro).setScale(2, RoundingMode.HALF_UP);
		
	}
	
	public static BigDecimal calculaTaxaCartao(BigDecimal valor, BigDecimal percentDesconto ){
		
		BigDecimal desconto = valor.divide(NUMERO_CEM).multiply(percentDesconto);			
		return valor.subtract(desconto.setScale(2, RoundingMode.HALF_UP));
	}
	
	public static BigDecimal calculaPercentualLucro(BigDecimal custoMercadoria, BigDecimal valorMercadoriaAvaliada) {
		return ((valorMercadoriaAvaliada.multiply(NUMERO_CEM)).divide(custoMercadoria,2, RoundingMode.HALF_UP)).subtract(NUMERO_CEM); 
				 
	}
}
