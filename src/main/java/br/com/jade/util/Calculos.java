package br.com.jade.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculos {

	public static BigDecimal calculaCustoEfetivo(int pecas, BigDecimal desconto, BigDecimal precoCusto, BigDecimal custoTotal){
		
		BigDecimal custoEfetivo = null;
		BigDecimal custoPecaAdcional = custoTotal.divide(BigDecimal.valueOf(pecas), 2, RoundingMode.HALF_UP);
		
		if(desconto != null || desconto != BigDecimal.valueOf(0)){
			BigDecimal cem = new BigDecimal(100);			
			BigDecimal descontoDecimal  = desconto.divide(cem,2, RoundingMode.HALF_UP );
			BigDecimal valorDesconto = descontoDecimal.multiply(precoCusto);
			BigDecimal precoComDesconto = precoCusto.subtract(valorDesconto);
			custoEfetivo = custoPecaAdcional.add(precoComDesconto);
			return custoEfetivo.setScale(2, RoundingMode.HALF_UP);
		}
		
		custoEfetivo = precoCusto.add(custoPecaAdcional);
		return custoEfetivo.setScale(2, RoundingMode.HALF_UP);		
	}
	
	public static BigDecimal calculaPrecoVenda(BigDecimal margemLucro, BigDecimal custoEfetivo){
				
		BigDecimal cem = new BigDecimal(100);
		BigDecimal margemDecimal = margemLucro.divide(cem, 2, RoundingMode.HALF_UP);
		BigDecimal lucro = margemDecimal.multiply(custoEfetivo);
		
		return custoEfetivo.add(lucro).setScale(2, RoundingMode.HALF_UP);
		
	}
}
