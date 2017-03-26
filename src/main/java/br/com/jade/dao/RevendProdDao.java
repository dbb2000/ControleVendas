package br.com.jade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.com.jade.enums.Localidade;
import br.com.jade.enums.Status;
import br.com.jade.model.Produto;
import br.com.jade.model.Revendedor;
import br.com.jade.model.Venda;
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
	
	public void atualizar(Produto produto, String apelido){
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Venda venda  = manager.merge(produto.getVenda());
		Produto prod = manager.merge(produto);
		
		if(prod.getStatus().equalsIgnoreCase(Status.VENDIDO.getStatus())){
			prod.getVenda().setPrecoVenda(prod.getPrecoVenda());
			prod.getVenda().setVendedor(apelido);
		}else {
			prod.getVenda().setDataVenda(null);
			prod.getVenda().setFormaPagamento(null);
			prod.getVenda().setPrecoVenda(null);
		}
				
		manager.persist(prod.getVenda());		
		manager.persist(prod);		
		tx.commit();
		manager.close();
	}

	public void gravar(Revendedor selectedRevendedor) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		Revendedor revendedor =  manager.merge(selectedRevendedor);
		
		for( Produto produto: revendedor.getProdutos()){
			produto.setLocalidade(Localidade.REVENDEDOR.getLocalidade());
			manager.persist(produto);
		}
		
		manager.persist(revendedor);

		tx.commit();
		manager.close();
	}

	public void atualizaStatus(Produto produto) {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		produto.setLocalidade(Localidade.LOJA.getLocalidade());
		
		tx.commit();
		manager.close();
		
		
	}
	
    public void removerProdutoVendido(Revendedor revendedor, Produto produto){
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
    	
		Revendedor revend = manager.merge(revendedor);
		
		revend.getProdutos().remove(produto);
		manager.persist(revend);
		
		tx.commit();
		manager.close();
    }
	
	
	
	
	
	
	
	
	
	
	
	
}
