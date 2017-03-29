package br.com.jade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import br.com.jade.model.Revendedor;
import br.com.jade.util.JpaUtil;

//@ManagedBean(name = "revendedorDao")
//@RequestScoped
public class RevendedorDao {

	private EntityManager manager = JpaUtil.getEntityManager();
	
	public void gravar(Revendedor revendedor) {

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Revendedor buscado = manager.find(Revendedor.class, revendedor.getApelido());
		
		if(buscado == null){
			manager.persist(revendedor);			
	
		}else {
			buscado.setApelido(revendedor.getApelido());
			buscado.setCep(revendedor.getCep());
			buscado.setCidade(revendedor.getCidade());
			buscado.setComplemento(revendedor.getComplemento());
			buscado.setCpf(revendedor.getCpf());
			buscado.setEndereço(revendedor.getEndereço());
			buscado.setFoneCelular(revendedor.getFoneCelular());
			buscado.setFoneResidencial(revendedor.getFoneResidencial());
			buscado.setNomeCompleto(revendedor.getNomeCompleto());
			buscado.setProdutos(revendedor.getProdutos());
			buscado.setRg(revendedor.getRg());
			buscado.setUf(revendedor.getUf());
			buscado.setBairro(revendedor.getBairro());
		}		
		tx.commit();
//		manager.close();	
	}
	
	public void excluir( Revendedor revendedor){

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		Revendedor reven = manager.find(Revendedor.class, revendedor.getApelido());
		manager.remove(reven);
		tx.commit();
//		manager.close();
	}
	
	public List<Revendedor> getRevendedores() {

//		Query query = manager.createQuery("from Produto");
		TypedQuery<Revendedor> query = manager.createQuery(
				"from Revendedor", Revendedor.class);
		List<Revendedor> revendedores = query.getResultList();
		
		return revendedores;
	}
}
