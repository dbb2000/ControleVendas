package br.com.jade.dao;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;

import br.com.jade.enums.Localidade;
import br.com.jade.enums.Status;
import br.com.jade.model.Produto;
import br.com.jade.model.Revendedor;
import br.com.jade.util.JpaUtil;

@ManagedBean(name = "revendProdDao")
@SessionScoped
public class RevendProdDao {
	
	private EntityManager manager = JpaUtil.getEntityManager();

	private SessionFactory sessionFactory;
	
	public List<Produto> getProdutos() {

		TypedQuery<Produto> query = manager.createQuery(
				"select p from Produto "
				+ "p inner join fetch p.venda v "
				+ "where status = :status and localidade = :localidade",
				Produto.class);
		query.setParameter("status", Status.DISPONIVEL.getStatus());
		query.setParameter("localidade", Localidade.LOJA.getLocalidade());
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

	public void gravar(Revendedor selectedRevendedor) {
		
		List<Produto> lista = new ArrayList<>();
		for(Produto produto : selectedRevendedor.getProdutos()){
			
			lista.add(produto);
		}
		
		selectedRevendedor.getProdutos().remove(0);
				
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		for(Produto produto : lista){
			
			produto.setLocalidade(Localidade.REVENDEDOR.getLocalidade());
			manager.persist(produto);
			selectedRevendedor.getProdutos().add(produto);
			manager.persist(selectedRevendedor);
		}
		

		
		tx.commit();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
