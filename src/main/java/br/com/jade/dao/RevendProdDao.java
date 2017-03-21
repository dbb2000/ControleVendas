package br.com.jade.dao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.jade.enums.Localidade;
import br.com.jade.enums.Status;
import br.com.jade.model.Produto;
import br.com.jade.util.JpaUtil;

@ManagedBean(name = "revendProdDao")
@SessionScoped
public class RevendProdDao {
	
	private EntityManager manager = JpaUtil.getEntityManager();

	public List<Produto> getProdutos() {

//		Query query = manager.createQuery("from Produto");
		TypedQuery<Produto> query = manager.createQuery(
				"select p from Produto p inner join p.venda v where status = :status and localidade = :localidade",
				Produto.class);
		query.setParameter("status", Status.DISPONIVEL.getStatus());
		query.setParameter("localidade", Localidade.LOJA.getLocalidade());
		List<Produto> produtos = query.getResultList();
		
		return produtos;
	}
}
