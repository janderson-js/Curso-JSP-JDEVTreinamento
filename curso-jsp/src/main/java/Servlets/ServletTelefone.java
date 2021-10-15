package Servlets;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ModelLogin;
import model.ModelTelefone;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servletTelefone")
public class ServletTelefone extends ServletGenericUtil  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository daoUser = new DAOUsuarioRepository();
	private DAOTelefoneRepository daoTel = new DAOTelefoneRepository();
 
    public ServletTelefone() {
 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			String idUser = request.getParameter("idUser");
			String acao = request.getParameter("acao");
			
			if(acao != null && !acao.isEmpty() && acao.equals("excluir")) {
				String idTel = request.getParameter("id");
				String idPai = request.getParameter("idPai");
				daoTel.deletarTelefone(Long.parseLong(idTel));
				
				ModelLogin modelLogin = daoUser.consultarUsuarioTelefone(Long.parseLong(idPai));
				
				List<ModelTelefone> modelTelefones = daoTel.listaTelefoneUsers(modelLogin.getId());
				
				request.setAttribute("modelTelefones", modelTelefones);
				request.setAttribute("load", "Telefones do Usuário");
				
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
				
				return;
			}
			
			if(idUser != null && !idUser.isEmpty()) {
			
				ModelLogin modelLogin = daoUser.consultarUsuarioTelefone(Long.parseLong(idUser));
				
				List<ModelTelefone> modelTelefones = daoTel.listaTelefoneUsers(modelLogin.getId());
				
				request.setAttribute("modelTelefones", modelTelefones);
				request.setAttribute("load", "Telefones do Usuário");
				
				request.setAttribute("modelLogin", modelLogin);
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
		
		try {
			
			String usuario_pai_id = request.getParameter("id");
			String numero = request.getParameter("numero");
			String msg = "Salvo com sucesso!!";
			
			
			if(!daoTel.existeTelefone(numero, Long.parseLong(usuario_pai_id))) {
							
				ModelTelefone modelTelefone = new ModelTelefone();
				
				modelTelefone.setUsuarioPaiId(daoUser.consultarUsuarioTelefone(Long.parseLong(usuario_pai_id)));
				modelTelefone.setNumero(numero);
				modelTelefone.setUsuarioCadId(super.getUserLogadoObj(request));
				
				daoTel.gravaTelefone(modelTelefone);
			
			}else {
				msg = "Telefone já existe!!!";
			}
			
			List<ModelTelefone> modelTelefones = daoTel.listaTelefoneUsers(Long.parseLong(usuario_pai_id));
			ModelLogin modelLogin = daoUser.consultarUsuarioTelefone(Long.parseLong(usuario_pai_id));
			
			request.setAttribute("modelLogin", modelLogin);
			request.setAttribute("modelTelefones", modelTelefones);
			request.setAttribute("load", "Números do usuário!!");
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
