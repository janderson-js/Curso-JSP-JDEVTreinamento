package Servlets;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import dao.DAOUsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/servletTelefone")
public class ServletTelefone extends ServletGenericUtil  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository daoUser = new DAOUsuarioRepository();
 
    public ServletTelefone() {
 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idUser = request.getParameter("idUser");
		try {
			if(idUser != null && !idUser.isEmpty()) {
			
				ModelLogin modelLogin = daoUser.consultarUsuarioTelefone(Long.parseLong(idUser));
				
				request.setAttribute("usuario", modelLogin);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
								
			}else {
				List<ModelLogin> modelLogins  = daoUser.listarUsers(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("load", "Usuários carregados");
				request.setAttribute("totalPagina", daoUser.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
