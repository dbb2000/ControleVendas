package br.com.jade.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import br.com.jade.util.JpaUtil;

public class EstatisticoDao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2410045853541510854L;
	private EntityManager manager = JpaUtil.getEntityManager();
	
	
	
}
