package Servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import beandto.BeanDtoGraficoSalarioUser;
import dao.DAOUsuarioRepository;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.ModelLogin;
import util.ReportUtil;

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
				request.setAttribute("load", "Usuários carregados");

				request.setAttribute("msg", "Excluido com sucesso!!");
				request.setAttribute("totalPagina", daoUser.totalPagina(this.getUserLogado(request)));
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
				
				response.addHeader("totalPaginaAjax", ""+daoUser.buscarUserTotalPaginacao(nomeBuscar, super.getUserLogado(request)));
				response.getWriter().write(json);
								
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjaxPage")) {
				
				String nomeBuscar = request.getParameter("nomeBuscar");
				String pagina = request.getParameter("pagina");

				List<ModelLogin> dadosJsonUser = daoUser.listarUsersPaginadoOffset(nomeBuscar,super.getUserLogado(request),Integer.parseInt(pagina));
				
				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(dadosJsonUser);
				
				response.addHeader("totalPaginaAjax", ""+daoUser.buscarUserTotalPaginacao(nomeBuscar, super.getUserLogado(request)));
				response.getWriter().write(json);
				
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {			

				String id = request.getParameter("id");

				ModelLogin modelLogin =  daoUser.consultarIdUsuario(id,super.getUserLogado(request));
				
				List<ModelLogin> modelLogins  = daoUser.listarUsers(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("load", "Usuários carregados");
									
				request.setAttribute("msg", "Usuário em Edição");
				request.setAttribute("modelLogin", modelLogin);
				request.setAttribute("totalPagina", daoUser.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
								
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")){
				
				List<ModelLogin> modelLogins  = daoUser.listarUsers(super.getUserLogado(request));
				
				request.setAttribute("load", "Usuário carregados");
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagina", daoUser.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downLoadFoto")){
				
				String idUser = request.getParameter("id");
				
				ModelLogin modelLogin = daoUser.consultarIdUsuario(idUser, super.getUserLogado(request));
				
				if(modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
						response.setHeader("Content-Disposition", "attachment;filename=arquivo."+ modelLogin.getExtensaoFotoUser());
						response.getOutputStream().write(new Base64().decode(modelLogin.getFotoUser().split("\\,")[1]));
				}
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")){
				
				Integer offset = Integer.parseInt(request.getParameter("pagina"));
				
				List<ModelLogin> modelLogin = daoUser.listarUsersPaginado(this.getUserLogado(request), offset);
				
				request.setAttribute("modelLogins", modelLogin);
				request.setAttribute("load", "Usuários carregados");
				request.setAttribute("totalPagina", daoUser.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioUser")){
				
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				if(dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {
					
					request.setAttribute("listaUser", daoUser.listaRel(super.getUserLogado(request)));
					
				}else {
					
					request.setAttribute("listaUser", daoUser.listaRelPorData(super.getUserLogado(request),dataInicial, dataFinal));
					
				}
				
				request.setAttribute("dataInicial", dataInicial);
				request.setAttribute("dataFinal", dataFinal);
				request.getRequestDispatcher("principal/reluser.jsp").forward(request, response);

				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioPDF")){
				
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				List<ModelLogin> modelLogins = null;
				
				if(dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {
				
					modelLogins = daoUser.listaRel(super.getUserLogado(request));
					
				}else {
					
					modelLogins = daoUser.listaRelPorData(super.getUserLogado(request),dataInicial, dataFinal);
					
				}
				
				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("PARAMETER_SUB_REPORT", request.getServletContext().getRealPath("relatorio") + File.separator );
				
				byte[] relatorio = new ReportUtil().geraRelatorioPDF(modelLogins, "rel-user-jsp", params, request.getServletContext());
				
				response.setHeader("Content-Disposition", "attachment;filename=arquivo.pdf");
				response.getOutputStream().write(relatorio);
				
			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("graficoSalario")){
				
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				if(dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {
					
					BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser = daoUser.montarGraficoSalarial(super.getUserLogado(request));	
					
					ObjectMapper objectMapper = new ObjectMapper();
					String json = objectMapper.writeValueAsString(beanDtoGraficoSalarioUser);
					
					response.getWriter().write(json);
					
				}else {
					
					BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser = daoUser.montarGraficoSalarial(super.getUserLogado(request), dataInicial, dataFinal);	
					
					ObjectMapper objectMapper = new ObjectMapper();
					String json = objectMapper.writeValueAsString(beanDtoGraficoSalarioUser);
					
					response.getWriter().write(json);
					
				}
			
			}else {
				List<ModelLogin> modelLogins  = daoUser.listarUsers(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("load", "Usuários carregados");
				request.setAttribute("totalPagina", daoUser.totalPagina(this.getUserLogado(request)));
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
			String msg = "operação realizado com sucesso!!!";
			String cep = request.getParameter("cep");
			String cidade = request.getParameter("cidade");
			String bairro = request.getParameter("bairro");
			String uf = request.getParameter("uf");
			String logradouro = request.getParameter("logradouro");
			String numero = request.getParameter("numero");
			String dataNascimento = request.getParameter("dataNascimento");
			String rendaMensal = request.getParameter("rendaMensal").split("\\ ")[1];
			
			rendaMensal = rendaMensal.replaceAll("\\.", "").replaceAll("\\,", ".");

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
			modelLogin.setDataNascimento(Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataNascimento))));
			modelLogin.setRendaMensal(Double.parseDouble(rendaMensal));
			
			if(request.getPart("filefoto") != null) {
				Part part = request.getPart("filefoto");
				if(part.getSize() > 0){

					byte[] foto = IOUtils.toByteArray(part.getInputStream());
					String  ImgBase64 = "data:image/"+ part.getContentType().split("\\/")[1] + ";base64," + new Base64().encodeBase64String(foto);
					
					modelLogin.setFotoUser(ImgBase64);
					modelLogin.setExtensaoFotoUser(part.getContentType().split("\\/")[1]);
				}
			}
			
			

			if (daoUser.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "Já existe um usuário com esse login!!! Por favor informe outro login!!";
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
			request.setAttribute("load", "Usuários carregados");

			request.setAttribute("msg", msg);
			request.setAttribute("modelLogin", modelLogin);
			request.setAttribute("totalPagina", daoUser.totalPagina(this.getUserLogado(request)));
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redireciona = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redireciona.forward(request, response);
		}
	}

}
