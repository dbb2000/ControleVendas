package br.com.jade.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.jade.dao.UsuarioDAO;
import br.com.jade.model.Usuario;

@ManagedBean(name = "LoginMB")
@SessionScoped
public class LoginManagedBean {

	private static boolean autenticado = false;
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario usuario = new Usuario();

	public String envia() {

		usuario = usuarioDAO.getUsuario(usuario.getNomeUsuario(), usuario.getSenha());
		if (usuario == null) {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
							"Erro no Login!"));
			return null;
		} else {
			LoginManagedBean.autenticado = true;
			return "/main";
		}


	}
	
	public static boolean isAutenticado(){
		return LoginManagedBean.autenticado;
	}
	
	public static String verificaAutenticacao(){
		if( isAutenticado() == false){
			return "index";
		}else {
			return null;
		}
			
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}