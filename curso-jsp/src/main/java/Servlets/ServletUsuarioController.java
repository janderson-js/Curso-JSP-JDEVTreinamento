package Servlets;

import java.io.IOException;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet("/servletUsuarioController")
public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository  daoUser = new DAOUsuarioRepository();

    public ServletUsuarioController() {
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String id = request.getParameter("id");
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setLogin(login);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setSenha(senha);
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			
			daoUser.gravarUsuario(modelLogin);
			
			request.setAttribute("msg", "opera��o realizado com sucesso!!!");
			request.setAttribute("modelLogin", modelLogin);
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redireciona = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redireciona.forward(request, response);
		}
	}

}
