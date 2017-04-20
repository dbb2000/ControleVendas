package br.com.jade.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.jade.model.Usuario;
import br.com.jade.util.JpaUtil;

public class UsuarioDAO {
	   
   // private EntityManagerFactory factory = Persistence.createEntityManagerFactory("usuarios");
    private EntityManager em = JpaUtil.getEntityManager();

    public Usuario getUsuario(String nomeUsuario, String senha) {

          try {
                Usuario usuario = (Usuario) em
                           .createQuery(
                                       "SELECT u from Usuario u where u.nomeUsuario = :name and u.senha = :senha")
                           .setParameter("name", nomeUsuario)
                           .setParameter("senha", senha).getSingleResult();

                return usuario;
          } catch (NoResultException e) {
                return null;
          }
    }

  public boolean inserirUsuario(Usuario usuario) {
          try {
                em.persist(usuario);
                return true;
          } catch (Exception e) {
                e.printStackTrace();
                return false;
          }
    }
    
    public boolean deletarUsuario(Usuario usuario) {
          try {
                em.remove(usuario);
                return true;
          } catch (Exception e) {
                e.printStackTrace();
                return false;
          }
    }

}