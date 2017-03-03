package br.com.jade.dao;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jade.enums.Localidade;
import br.com.jade.enums.Status;
import br.com.jade.model.Custo;
import br.com.jade.model.Produto;
import br.com.jade.model.Venda;
import br.com.jade.util.JpaUtil;

@ManagedBean(name = "custoDao")
@ApplicationScoped
public class CustoDao {

	private EntityManager manager = JpaUtil.getEntityManager();
	
	public void gravar(Custo custo) {
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
				
//		produto.setLocalidade(Localidade.LOJA.getLocalidade());
//		produto.setStatus(Status.DISPONIVEL.getStatus());
//		
		manager.persist(custo);		
//		produto.setVenda(venda);
//		manager.persist(produto);
		
		tx.commit();
	}
}
