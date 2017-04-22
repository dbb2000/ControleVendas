package br.com.jade.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.google.common.collect.Lists;
import com.google.common.primitives.Longs;

import br.com.jade.dao.ProdutoDao;
import br.com.jade.model.Produto;

@FacesConverter("produtosListConverter")
public class ProdutosListConverter implements Converter {

	private static final String SEPARADOR = ",";
	private ProdutoDao produtoDao = new ProdutoDao();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value == "") {
            return null;
        }
        
        List<Produto> lista = new ArrayList<>();
        List<Long> longList = Lists.transform(Arrays.asList(value.split(SEPARADOR)), Longs.stringConverter());
        lista.addAll(produtoDao.getProdutos(longList));
        return lista;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null){
			return null;
		}
		
		@SuppressWarnings("unchecked")
		List<Produto> lista = (List<Produto>) value;
		StringBuilder sb = new StringBuilder();
		boolean primeiro = true;
		for ( Produto produto : lista){
			
			if(primeiro){
				primeiro = false;
			} else {
				sb.append(SEPARADOR);
			}
			sb.append(produto.getId());
		}
		
		return sb.toString();
	}

	
}
