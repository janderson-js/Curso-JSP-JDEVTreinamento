package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;

@MultipartConfig
@WebServlet(urlPatterns = {"/servletUsuarioController"})
public class ServletUsuarioController extends ServletGenericUtil {
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
				
				List<ModelLogin> modelLogins  = daoUser.listarUsers(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("load", "Usu�rios carregados");

				request.setAttribute("msg", "Excluido com sucesso!!");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax")) {

				String idUser = request.getParameter("id");

				daoUser.deletarUser(idUser);
				
				response.getWriter().write("Excluido com sucesso!!");
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {

				String nomeBuscar = request.getParameter("nomeBuscar");

				List<ModelLogin> dadosJsonUser = daoUser.buscarUser(nomeBuscar,super.getUserLogado(request));
				
				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(dadosJsonUser);
				
				response.getWriter().write(json);
								
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {

				String id = request.getParameter("id");

				ModelLogin modelLogin =  daoUser.consultarIdUsuario(id,super.getUserLogado(request));
				
				List<ModelLogin> modelLogins  = daoUser.listarUsers(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("load", "Usu�rios carregados");
									
				request.setAttribute("msg", "Usu�rio em Edi��o");
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
								
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")){
				
				List<ModelLogin> modelLogins  = daoUser.listarUsers(super.getUserLogado(request));
				
				request.setAttribute("load", "Usu�rio carregados");
				request.setAttribute("modelLogins", modelLogins);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downLoadFoto")){
				
				String idUser = request.getParameter("id");
				
				ModelLogin modelLogin = daoUser.consultarIdUsuario(idUser, super.getUserLogado(request));
				
				if(modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
						response.setHeader("Content-Disposition", "attachment;filename=arquivo."+ modelLogin.getExtensaoFotoUser());
						response.getOutputStream().write(new Base64().decode(modelLogin.getFotoUser().split("\\,")[1]));
				}
				
			}else {
				List<ModelLogin> modelLogins  = daoUser.listarUsers(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("load", "Usu�rios carregados");
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
			String perfil = request.getParameter("perfil");
			String sexo[] = request.getParameterValues("sexo");
			String msg = "opera��o realizado com sucesso!!!";
			String cep = request.getParameter("cep");
			String cidade = request.getParameter("cidade");
			String bairro = request.getParameter("bairro");
			String uf = request.getParameter("uf");
			String logradouro = request.getParameter("logradouro");
			String numero = request.getParameter("numero");

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setLogin(login);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setSenha(senha);
			modelLogin.setPerfil(perfil);
			modelLogin.setSexo(sexo[0]);
			modelLogin.setCep(cep);
			modelLogin.setCidade(cidade);
			modelLogin.setBairro(bairro);
			modelLogin.setUf(uf);
			modelLogin.setLogradouro(logradouro);
			modelLogin.setNumero(numero);
			
			if(ServletFileUpload.isMultipartContent(request)) {
				Part part = request.getPart("filefoto");
				if(part.getSize() > 0){

					byte[] foto = IOUtils.toByteArray(part.getInputStream());
					String  ImgBase64 = "data:image/"+ part.getContentType().split("\\/")[1] + ";base64," + new Base64().encodeBase64String(foto);
					
					modelLogin.setFotoUser(ImgBase64);
					modelLogin.setExtensaoFotoUser(part.getContentType().split("\\/")[1]);
				}
			}
			
			

			if (daoUser.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "J� existe um usu�rio com esse login!!! Por favor informe outro login!!";
			} else {

				if (modelLogin.isNovo()) {
					msg = "Gravado com sucesso!!";
				} else {
					msg = "Atualizado com sucesso!!";
				}

				modelLogin = daoUser.gravarUsuario(modelLogin,super.getUserLogado(request));
			}
			
			List<ModelLogin> modelLogins  = daoUser.listarUsers(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins);
			request.setAttribute("load", "Usu�rios carregados");

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
