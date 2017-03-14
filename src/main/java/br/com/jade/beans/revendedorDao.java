package br.com.jade.beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jade.model.Revendedor;
import br.com.jade.util.JpaUtil;

@ManagedBean(name = "revendedorDao")
@ApplicationScoped
public class revendedorDao {

	private EntityManager manager = JpaUtil.getEntityManager();
	
	public void gravar(Revendedor revendedor) {
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		manager.persist(revendedor);		
		
		tx.commit();
	}
}
