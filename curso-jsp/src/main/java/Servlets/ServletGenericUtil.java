package Servlets;

import java.io.Serializable;

import dao.DAOUsuarioRepository;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.ModelLogin;

public class ServletGenericUtil extends HttpServlet implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private DAOUsuarioRepository daoUser = new DAOUsuarioRepository();
	
	
	public Long getUserLogado(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
				
		String usuariologado = (String) session.getAttribute("usuario");
		
		return daoUser.consultarUsuarioLogado(usuariologado).getId();
		
	}
	
	public ModelLogin getUserLogadoObj(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
				
		String usuariologado = (String) session.getAttribute("usuario");
		
		return daoUser.consultarUsuarioLogado(usuariologado);
		
	}

}
