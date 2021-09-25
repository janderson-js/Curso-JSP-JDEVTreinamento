package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet(urlPatterns = {"/servletUsuarioController"})
public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUser = new DAOUsuarioRepository();

	public ServletUsuarioController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {

				String idUser = request.getParameter("id");

				daoUser.deletarUser(idUser);
				
				List<ModelLogin> modelLogins  = daoUser.listarUsers();
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("load", "Usuários carregados");

				request.setAttribute("msg", "Excluido com sucesso!!");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax")) {

				String idUser = request.getParameter("id");

				daoUser.deletarUser(idUser);
				
				response.getWriter().write("Excluido com sucesso!!");
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {

				String nomeBuscar = request.getParameter("nomeBuscar");

				List<ModelLogin> dadosJsonUser = daoUser.buscarUser(nomeBuscar);
				
				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(dadosJsonUser);
				
				response.getWriter().write(json);
								
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {

				String id = request.getParameter("id");

				ModelLogin modelLogin =  daoUser.consultarIdUsuario(id);
				
				List<ModelLogin> modelLogins  = daoUser.listarUsers();
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("load", "Usuários carregados");
									
				request.setAttribute("msg", "Usuário em Edição");
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
								
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")){
				
				List<ModelLogin> modelLogins  = daoUser.listarUsers();
				
				request.setAttribute("load", "Usuário carregados");
				request.setAttribute("modelLogins", modelLogins);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}else {
				List<ModelLogin> modelLogins  = daoUser.listarUsers();
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("load", "Usuários carregados");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}

			

		} catch (Exception e) {

			e.printStackTrace();
			RequestDispatcher redireciona = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redireciona.forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String id = request.getParameter("id");
			String msg = "operação realizado com sucesso!!!";

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setLogin(login);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setSenha(senha);
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);

			if (daoUser.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "Já existe um usuário com esse login!!! Por favor informe outro login!!";
			} else {

				if (modelLogin.isNovo()) {
					msg = "Gravado com sucesso!!";
				} else {
					msg = "Atualizado com sucesso!!";
				}

				modelLogin = daoUser.gravarUsuario(modelLogin);
			}
			
			List<ModelLogin> modelLogins  = daoUser.listarUsers();
			request.setAttribute("modelLogins", modelLogins);
			request.setAttribute("load", "Usuários carregados");

			request.setAttribute("msg", msg);
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
