package br.com.jade.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jade.model.Custo;
import br.com.jade.util.JpaUtil;

public class CustoDao {

	private EntityManager manager = JpaUtil.getEntityManager();
	
	public void gravar(Custo custo) {
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();

		manager.persist(custo);		
		
		tx.commit();
	}

	public void atualizarQtdePecas(Custo custo , int numPecas){
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		custo.setQtdePecas(numPecas);
		manager.persist(custo);
		tx.commit();
	}
	//FIXME verificar a sessão da tela de custo que parece não estar correta. fui tentar salvar dois arquivos cvs de uma vez e não rolou
}
