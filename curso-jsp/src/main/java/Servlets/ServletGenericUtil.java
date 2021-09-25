package Servlets;

import java.io.Serializable;

import dao.DAOUsuarioRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ServletGenericUtil extends HttpServlet implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private DAOUsuarioRepository daoUser = new DAOUsuarioRepository();
	
	
	public Long getUserLogado(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
				
		String usuariologado = (String) session.getAttribute("usuario");
		
		return daoUser.consultarUsuarioLogado(usuariologado).getId();
		
	}

}
