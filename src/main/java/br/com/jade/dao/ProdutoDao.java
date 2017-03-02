package br.com.jade.dao;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.apache.commons.io.IOUtils;

import br.com.jade.enums.Localidade;
import br.com.jade.enums.Status;
import br.com.jade.model.Custo;
import br.com.jade.model.Produto;
import br.com.jade.model.Venda;
import br.com.jade.util.Calculos;
import br.com.jade.util.JpaUtil;

@ManagedBean(name = "produtoDao")
@ApplicationScoped
public class ProdutoDao {

	private EntityManager manager = JpaUtil.getEntityManager();


	public List<Produto> getProdutos() {

//		Query query = manager.createQuery("from Produto");
		TypedQuery<Produto> query = manager.createQuery(
				"select p from Produto p inner join p.venda v",
				Produto.class);
		List<Produto> produtos = query.getResultList();
		
		return produtos;
	}
	
	public void atualizar(Produto produto){
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		if(produto.getStatus().equalsIgnoreCase(Status.VENDIDO.getStatus())){
			produto.getVenda().setPrecoVenda(produto.getPrecoVenda());
		}else {
			produto.getVenda().setDataVenda(null);
			produto.getVenda().setFormaPagamento(null);
			produto.getVenda().setPrecoVenda(null);
		}
				
		manager.persist(produto.getVenda());		
		manager.persist(produto);
		
		tx.commit();
	}

	public void gravar(Produto produto) {
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		Venda venda = new Venda();
				
		produto.setLocalidade(Localidade.LOJA.getLocalidade());
		produto.setStatus(Status.DISPONIVEL.getStatus());
		
		manager.persist(venda);		
		produto.setVenda(venda);
		manager.persist(produto);
		
		tx.commit();
	}
	
	public void importar(InputStream file, Custo custo, Long margemLucro) throws IOException{

		// aqui crio duas cópias de InputStream, uma para contar linha e outra para iterar
		// pois não posso usar o mesmo InputStream duas vezes.
		byte[] byteArray = IOUtils.toByteArray(file); 
		InputStream input1 = new ByteArrayInputStream(byteArray);
	    InputStream input2 = new ByteArrayInputStream(byteArray);
		
		int totalLinhas = contarLinhas(input1);
		
		BufferedReader br = null;
		String line;
		
		try {
			br = new BufferedReader(new InputStreamReader(input2));
			while ((line = br.readLine()) != null) {
				
				// campo[0] = código
				// campo[1] = descrição
				// campo[2] = preço de custo
				String[] campos = line.split(";");
				
				BigDecimal custoEfetivo = Calculos.calculaCustoEfetivo(totalLinhas, custo.getDescontoRecebido(), new BigDecimal(campos[2]));
				BigDecimal precoVenda = Calculos.calculaPrecoVenda(margemLucro, custoEfetivo);
				
				Produto produto = new Produto();
				produto.setCodigo(campos[0]);
				produto.setDataEntrada(custo.getDataCompra());
				produto.setDescricao(campos[1]);
				produto.setPrecoCusto(new BigDecimal(campos[2]));
//				produto.setPrecoCustoEfetivo(custoEfetivo);
//				produto.setPrecoVenda(precoVenda);

				
//				gravar(produto);
			
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	private int contarLinhas (InputStream file) throws IOException{
		
		int numberOfLines = 0;

		try {
			Reader fr = new InputStreamReader(file);
			LineNumberReader lnr = new LineNumberReader(fr);
			lnr.skip(Long.MAX_VALUE);
			numberOfLines = lnr.getLineNumber();

		}
		catch (Exception e){ 
			System.out.println(e); 
		}
		return numberOfLines;
	}


}








