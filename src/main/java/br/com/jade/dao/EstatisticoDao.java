package br.com.jade.dao;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.jade.enums.Localidade;
import br.com.jade.enums.Status;
import br.com.jade.util.JpaUtil;

public class EstatisticoDao implements Serializable{

	private static final long serialVersionUID = 2410045853541510854L;
	private EntityManager manager = JpaUtil.getEntityManager();
	
	
	public int getPecasDisponiveis() {
		Query query = manager.createQuery(
				"select count(p) from Produto p where p.status=:status");
		query.setParameter("status", Status.DISPONIVEL.getStatus());		
		return Integer.parseInt(query.getSingleResult().toString());
	}
	
	public int getPecasLoja() {
		Query query = manager.createQuery(
				"select count(p) from Produto p where p.status=:status and p.localidade=:localidade");
		query.setParameter("status", Status.DISPONIVEL.getStatus());
		query.setParameter("localidade", Localidade.LOJA.getLocalidade());		
		return Integer.parseInt(query.getSingleResult().toString());
	}
	
	public int getPecasRevendedor() {
		Query query = manager.createQuery(
				"select r.produtos from Revendedor r");		
		return query.getResultList().size(); 
	}
	public BigDecimal getCustoTotalMercadorias() {
		Query query = manager.createQuery(
				"select sum(p.precoCustoEfetivo) from Produto p where p.status=:status");
		query.setParameter("status", Status.DISPONIVEL.getStatus());		
		return new BigDecimal(query.getSingleResult().toString());
	}
	public BigDecimal getCustoMercadoriasAvaliado() {
		Query query = manager.createQuery(
				"select sum(p.precoVenda) from Produto p where p.status=:status");
		query.setParameter("status", Status.DISPONIVEL.getStatus());		
		return new BigDecimal(query.getSingleResult().toString());
	}
	
}
