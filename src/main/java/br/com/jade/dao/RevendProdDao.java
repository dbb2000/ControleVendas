package br.com.jade.dao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.com.jade.enums.Localidade;
import br.com.jade.enums.Status;
import br.com.jade.model.Produto;
import br.com.jade.model.Revendedor;
import br.com.jade.util.JpaUtil;

//@ManagedBean(name = "revendProdDao")
//@SessionScoped
public class RevendProdDao {
	
	


	
	public List<Produto> getProdutos() {
		EntityManager manager = JpaUtil.getEntityManager();
		TypedQuery<Produto> query = manager.createQuery(
				"select p from Produto "
				+ "p inner join fetch p.venda v "
				+ "where status = :status and localidade = :localidade",
				Produto.class);
		query.setParameter("status", Status.DISPONIVEL.getStatus());
		query.setParameter("localidade", Localidade.LOJA.getLocalidade());
		List<Produto> produtos = query.getResultList();
		manager.close();
		return produtos;
	}
	
	public void atualizar(Produto produto){
		EntityManager manager = JpaUtil.getEntityManager();
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
		manager.close();
	}

	public void gravar(Revendedor selectedRevendedor) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		manager.persist(selectedRevendedor);

		tx.commit();
		manager.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
