package br.com.jade.beans;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.com.jade.model.Produto;
import br.com.jade.model.Revendedor;
import br.com.jade.util.JpaUtil;

@ManagedBean(name = "revendedorDao")
@ApplicationScoped
public class RevendedorDao {

	private EntityManager manager = JpaUtil.getEntityManager();
	
	public void gravar(Revendedor revendedor) {
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		manager.persist(revendedor);		
		
		tx.commit();
	}
	
	public List<Revendedor> getRevendedores() {

//		Query query = manager.createQuery("from Produto");
		TypedQuery<Revendedor> query = manager.createQuery(
				"from Revendedor", Revendedor.class);
		List<Revendedor> revendedores = query.getResultList();
		
		return revendedores;
	}
}
