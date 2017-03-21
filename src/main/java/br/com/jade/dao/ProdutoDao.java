package br.com.jade.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.com.jade.enums.Localidade;
import br.com.jade.enums.Status;
import br.com.jade.model.Custo;
import br.com.jade.model.Produto;
import br.com.jade.model.Venda;
import br.com.jade.util.Calculos;
import br.com.jade.util.JpaUtil;

@ManagedBean(name = "produtoDao")
@ViewScoped
public class ProdutoDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -831991596841551692L;
	private EntityManager manager = JpaUtil.getEntityManager();


	public List<Produto> getProdutos() {

		TypedQuery<Produto> query = manager.createQuery(
				"select p from Produto p "
				+ "inner join fetch p.venda v",
				Produto.class);
		List<Produto> produtos = query.getResultList();
		
		return produtos;
	}
	
	public void atualizar(Produto produto){
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		if(produto.getStatus().equalsIgnoreCase(Status.VENDIDO.getStatus())){
			produto.getVenda().setPrecoVenda(produto.getPrecoVenda());
		}else {
			produto.getVenda().setDataVenda(null);
			produto.getVenda().setFormaPagamento(null);
			produto.getVenda().setPrecoVenda(null);
		}
				
		manager.persist(produto.getVenda());		
		manager.persist(produto);
		
		tx.commit();
	}

	public void gravar(Produto produto) {
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		Venda venda = new Venda();
				
		produto.setLocalidade(Localidade.LOJA.getLocalidade());
		produto.setStatus(Status.DISPONIVEL.getStatus());
		
		manager.persist(venda);		
		produto.setVenda(venda);
		manager.persist(produto);
		
		tx.commit();
	}
	
	public void importar(InputStream file, Custo custo, BigDecimal margemLucro, int totalProdutos) throws IOException{

		BufferedReader br = null;
		String line;
		
		try {
			br = new BufferedReader(new InputStreamReader(file));
			while ((line = br.readLine()) != null) {
				
				// campo[0] = código
				// campo[1] = descrição
				// campo[2] = preço de custo
				String[] campos = line.split(";");
				
				BigDecimal custoEfetivo = Calculos.calculaCustoEfetivo(totalProdutos, custo.getDescontoRecebido(), new BigDecimal(campos[2]), custo.getCustoTotal());
				BigDecimal precoVenda = Calculos.calculaPrecoVenda(margemLucro, custoEfetivo);
				
				Produto produto = new Produto();
				produto.setCodigo(campos[0]);
				produto.setDescricao(campos[1]);
				
				produto.setPrecoCusto(new BigDecimal(campos[2]));
				produto.setPrecoCustoEfetivo(custoEfetivo);
				produto.setPrecoVenda(precoVenda);				
				produto.setDataEntrada(custo.getDataCompra());
				produto.setCusto(custo);
	
				gravar(produto);			
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	



}








