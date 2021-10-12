package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import beandto.BeanDtoGraficoSalarioUser;
import connection.SingleConnectionBanco;
import model.ModelLogin;
import model.ModelTelefone;

public class DAOUsuarioRepository {

	private Connection connection;

	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}

	public ModelLogin gravarUsuario(ModelLogin modelLogin, Long userlogado) throws Exception {

		if (modelLogin.isNovo()) {
			String sql = "INSERT INTO model_login (login, senha, nome, email, usuario_id, perfil, sexo, cep, cidade, bairro, uf, logradouro, numero,datanascimento, renda_mensal) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement pstm = connection.prepareStatement(sql);

			pstm.setString(1, modelLogin.getLogin());
			pstm.setString(2, modelLogin.getSenha());
			pstm.setString(3, modelLogin.getNome());
			pstm.setString(4, modelLogin.getEmail());
			pstm.setLong(5, userlogado);
			pstm.setString(6, modelLogin.getPerfil());
			pstm.setString(7, modelLogin.getSexo());
			pstm.setString(8, modelLogin.getCep());
			pstm.setString(9, modelLogin.getCidade());
			pstm.setString(10, modelLogin.getBairro());
			pstm.setString(11, modelLogin.getUf());
			pstm.setString(12, modelLogin.getLogradouro());
			pstm.setString(13, modelLogin.getNumero());
			pstm.setDate(14, modelLogin.getDataNascimento());
			pstm.setDouble(15, modelLogin.getRendaMensal());
			pstm.execute();
			connection.commit();
			
			if(modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
				
				sql = "update model_login set fotouser=? , extensaofotouser=? where login=?";
				
				PreparedStatement pstmFoto = connection.prepareStatement(sql);
				
				pstmFoto.setString(1, modelLogin.getFotoUser());
				pstmFoto.setString(2, modelLogin.getExtensaoFotoUser());
				pstmFoto.setString(3, modelLogin.getLogin());
				
				pstmFoto.executeUpdate();
				
				connection.commit();
			}
			
		} else {
			String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=?, perfil=?, sexo=?, cep=?, cidade=?, bairro=?, uf=?, logradouro=?, numero=?, datanascimento=?,"
					+ " renda_mensal=? WHERE id = ?";

			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, modelLogin.getLogin());
			pstm.setString(2, modelLogin.getSenha());
			pstm.setString(3, modelLogin.getNome());
			pstm.setString(4, modelLogin.getEmail());
			pstm.setString(5, modelLogin.getPerfil());
			pstm.setString(6, modelLogin.getSexo());
			pstm.setString(7, modelLogin.getCep());
			pstm.setString(8, modelLogin.getCidade());
			pstm.setString(9, modelLogin.getBairro());
			pstm.setString(10, modelLogin.getUf());
			pstm.setString(11, modelLogin.getLogradouro());
			pstm.setString(12, modelLogin.getNumero());
			pstm.setDate(13, modelLogin.getDataNascimento());
			pstm.setDouble(14, modelLogin.getRendaMensal());
			pstm.setLong(15, modelLogin.getId());
			

			pstm.executeUpdate();
			connection.commit();
			
			if(modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
				
				sql = "update model_login set fotouser=? , extensaofotouser=? where id=?";
				
				PreparedStatement pstmFoto = connection.prepareStatement(sql);
				
				pstmFoto.setString(1, modelLogin.getFotoUser());
				pstmFoto.setString(2, modelLogin.getExtensaoFotoUser());
				pstmFoto.setLong(3, modelLogin.getId());
				
				pstmFoto.executeUpdate();
				
				connection.commit();
			}

		}

		return this.consultarUsuario(modelLogin.getLogin(), userlogado);

	}
	
	
	public ModelLogin consultarUsuarioLogado(String login) throws Exception {
		ModelLogin modelLogin = new ModelLogin();
		String sql = "Select * from model_login WHERE upper(login)=upper(?)";

		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, login);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {

			modelLogin.setId(rs.getLong("id"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setEmail(rs.getString("email"));
			modelLogin.setUserAdmin(rs.getBoolean("useradmin"));
			modelLogin.setPerfil(rs.getString("perfil"));
			modelLogin.setSexo(rs.getString("sexo"));
			modelLogin.setFotoUser(rs.getString("fotouser"));
			

		}
		connection.commit();

		return modelLogin;
	}
	
	public List<ModelLogin> listaRelPorData(Long userLogado, String dataInicial, String dataFinal) throws Exception {

		List<ModelLogin> user = new ArrayList<>();

		String sql = "select * from model_login Where usuario_id=? AND datanascimento >= ?  AND datanascimento <= ? AND useradmin is false ORDER BY id ASC";

		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, userLogado);
		pstm.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial))));
		pstm.setDate(3, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal))));
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setId(rs.getLong("id"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setDataNascimento(rs.getDate("datanascimento"));
			modelLogin.setRendaMensal(rs.getDouble("renda_mensal"));
			modelLogin.setEmail(rs.getString("email"));
			modelLogin.setPerfil(rs.getString("perfil"));
			modelLogin.setSexo(rs.getString("sexo"));
			modelLogin.setCep(rs.getString("cep"));
			modelLogin.setCidade(rs.getString("cidade"));
			modelLogin.setBairro(rs.getString("bairro"));
			modelLogin.setUf(rs.getString("uf"));
			modelLogin.setLogradouro(rs.getString("logradouro"));
			modelLogin.setNumero(rs.getString("numero"));
			
			modelLogin.setTelefones(this.listaTelefoneUsers(modelLogin.getId()));

			user.add(modelLogin);
		}
		connection.commit();

		return user;

	}
	
	public List<ModelLogin> listaRel(Long userLogado) throws Exception {

		List<ModelLogin> user = new ArrayList<>();

		String sql = "select * from model_login Where usuario_id=? AND useradmin is false ORDER BY id ASC";

		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, userLogado);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setId(rs.getLong("id"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setDataNascimento(rs.getDate("datanascimento"));
			modelLogin.setRendaMensal(rs.getDouble("renda_mensal"));
			modelLogin.setEmail(rs.getString("email"));
			modelLogin.setPerfil(rs.getString("perfil"));
			modelLogin.setSexo(rs.getString("sexo"));
			modelLogin.setCep(rs.getString("cep"));
			modelLogin.setCidade(rs.getString("cidade"));
			modelLogin.setBairro(rs.getString("bairro"));
			modelLogin.setUf(rs.getString("uf"));
			modelLogin.setLogradouro(rs.getString("logradouro"));
			modelLogin.setNumero(rs.getString("numero"));
			
			modelLogin.setTelefones(this.listaTelefoneUsers(modelLogin.getId()));

			user.add(modelLogin);
		}
		connection.commit();

		return user;

	}
	
	public List<ModelTelefone> listaTelefoneUsers(Long idUserPai) throws Exception{
		
		List<ModelTelefone> telefones = new ArrayList<ModelTelefone>();
		DAOUsuarioRepository daoUser = new DAOUsuarioRepository();
		
		String sql = "Select * from telefone WHERE usuario_pai_id=?";
		PreparedStatement pstm = connection.prepareStatement(sql);		
		pstm.setLong(1, idUserPai);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			ModelTelefone telefone = new ModelTelefone();
			
			telefone.setId(rs.getLong("id"));
			telefone.setNumero(rs.getString("numero"));
			telefone.setUsuarioPaiId(this.consultarUsuarioTelefone(rs.getLong("usuario_pai_id")));
			telefone.setUsuarioCadId(this.consultarUsuarioTelefone(rs.getLong("usuario_cad_id")));
			
			telefones.add(telefone);
		}
		
		return telefones;
	}
	
	public ModelLogin consultarUsuarioTelefone(Long id) throws Exception {
		ModelLogin modelLogin = new ModelLogin();
		String sql = "Select * from model_login WHERE id = ? AND useradmin is false";

		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, id);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {

			modelLogin.setId(rs.getLong("id"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setEmail(rs.getString("email"));
			modelLogin.setPerfil(rs.getString("perfil"));
			modelLogin.setSexo(rs.getString("sexo"));
			modelLogin.setFotoUser(rs.getString("fotouser"));

		}
		connection.commit();

		return modelLogin;

	}

	public ModelLogin consultarUsuario(String login, Long userLogado) throws Exception {
		ModelLogin modelLogin = new ModelLogin();
		String sql = "Select * from model_login WHERE upper(login)=upper(?) AND useradmin is false AND usuario_id=? limit 5";

		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, login);
		pstm.setLong(2, userLogado);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {

			modelLogin.setId(rs.getLong("id"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setDataNascimento(rs.getDate("datanascimento"));
			modelLogin.setRendaMensal(rs.getDouble("renda_mensal"));
			modelLogin.setEmail(rs.getString("email"));
			modelLogin.setPerfil(rs.getString("perfil"));
			modelLogin.setSexo(rs.getString("sexo"));
			modelLogin.setFotoUser(rs.getString("fotouser"));
			modelLogin.setCep(rs.getString("cep"));
			modelLogin.setCidade(rs.getString("cidade"));
			modelLogin.setBairro(rs.getString("bairro"));
			modelLogin.setUf(rs.getString("uf"));
			modelLogin.setLogradouro(rs.getString("logradouro"));
			modelLogin.setNumero(rs.getString("numero"));

		}
		connection.commit();

		return modelLogin;

	}

	public ModelLogin consultarIdUsuario(String id, Long userLogado) throws Exception {
		ModelLogin modelLogin = new ModelLogin();
		String sql = "Select * from model_login WHERE id=? AND useradmin is false AND usuario_id=?";

		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, Long.parseLong(id));
		pstm.setLong(2, userLogado);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {

			modelLogin.setId(rs.getLong("id"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setDataNascimento(rs.getDate("datanascimento"));
			modelLogin.setRendaMensal(rs.getDouble("renda_mensal"));
			modelLogin.setEmail(rs.getString("email"));
			modelLogin.setPerfil(rs.getString("perfil"));
			modelLogin.setSexo(rs.getString("sexo"));
			modelLogin.setFotoUser(rs.getString("fotouser"));
			modelLogin.setExtensaoFotoUser(rs.getString("extensaofotouser"));
			modelLogin.setCep(rs.getString("cep"));
			modelLogin.setCidade(rs.getString("cidade"));
			modelLogin.setBairro(rs.getString("bairro"));
			modelLogin.setUf(rs.getString("uf"));
			modelLogin.setLogradouro(rs.getString("logradouro"));
			modelLogin.setNumero(rs.getString("numero"));

		}
		connection.commit();

		return modelLogin;

	}

	public boolean validarLogin(String login) throws Exception {

		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper(?);";

		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, login);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			return rs.getBoolean("existe");
		}

		return false;
	}

	public void deletarUser(String id) throws Exception {

		String sql = "DELETE FROM model_login WHERE id = ? AND useradmin is false";

		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, Long.parseLong(id));

		pstm.executeUpdate();
		connection.commit();
	}

	public int buscarUserTotalPaginacao(String nome, Long userLogado) throws Exception {

		String sql = "select count(1) as total from model_login where upper(nome) like upper(?) AND useradmin is false AND usuario_id = ?";

		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, "%" + nome + "%");
		pstm.setLong(2, userLogado);
		ResultSet rs = pstm.executeQuery();
		
		rs.next();
		
		Double cadastro = rs.getDouble("total");
		
		Double porpagina = 5.0;
		
		Double pagina = cadastro / porpagina;
		
		Double resto = pagina % 2;
		
		if(resto > 1) {
			pagina ++;
		}
		
		return pagina.intValue();
		
	}
	
	public List<ModelLogin> buscarUser(String nome, Long userLogado) throws Exception {

		List<ModelLogin> user = new ArrayList<>();

		String sql = "select * from model_login where upper(nome) like upper(?) AND useradmin is false AND usuario_id = ? limit 5";

		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, "%" + nome + "%");
		pstm.setLong(2, userLogado);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setId(rs.getLong("id"));
			modelLogin.setLogin(rs.getString("login"));
			// modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setEmail(rs.getString("email"));
			

			user.add(modelLogin);
		}
		connection.commit();

		return user;

	}
	
	public int totalPagina(Long userLogado) throws Exception {
		
		String sql = "select count(1) as total from model_login where useradmin is false and usuario_id=?";
		
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, userLogado);		
		ResultSet rs = pstm.executeQuery();
		
		rs.next();
		
		Double cadastro = rs.getDouble("total");
		
		Double porpagina = 5.0;
		
		Double pagina = cadastro / porpagina;
		
		Double resto = pagina % 2;
		
		if(resto > 1) {
			pagina ++;
		}
		
		return pagina.intValue();
	}
	
	public List<ModelLogin> listarUsersPaginadoOffset(String nome,Long userLogado, int offset) throws Exception {

		List<ModelLogin> user = new ArrayList<>();

		String sql = "select * from model_login Where upper(nome) like upper(?) AND usuario_id=? AND useradmin is false ORDER BY nome ASC offset ? limit 5";

		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1,"%"+nome+"%");
		pstm.setLong(2, userLogado);
		pstm.setInt(3,offset);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setId(rs.getLong("id"));
			modelLogin.setLogin(rs.getString("login"));
			// modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setEmail(rs.getString("email"));

			user.add(modelLogin);
		}
		connection.commit();

		return user;

	}
	
	public List<ModelLogin> listarUsersPaginado(Long userLogado, Integer offset) throws Exception {

		List<ModelLogin> user = new ArrayList<>();

		String sql = "select * from model_login Where usuario_id=? AND useradmin is false ORDER BY nome ASC offset ? limit 5";

		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, userLogado);
		pstm.setInt(2, offset);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setId(rs.getLong("id"));
			modelLogin.setLogin(rs.getString("login"));
			// modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setEmail(rs.getString("email"));

			user.add(modelLogin);
		}
		connection.commit();

		return user;

	}

	public List<ModelLogin> listarUsers(Long userLogado) throws Exception {

		List<ModelLogin> user = new ArrayList<>();

		String sql = "select * from model_login Where usuario_id=? AND useradmin is false ORDER BY nome ASC offset 0 limit 5";

		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, userLogado);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setId(rs.getLong("id"));
			modelLogin.setLogin(rs.getString("login"));
			// modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setEmail(rs.getString("email"));

			user.add(modelLogin);
		}
		connection.commit();

		return user;

	}
	
	public BeanDtoGraficoSalarioUser montarGraficoSalarial(Long userLogado) throws Exception{
		
		

		String sql = "SELECT avg(renda_mensal) as media_salarial, perfil from model_login where usuario_id = ? group by perfil;";

		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, userLogado);
		ResultSet rs = pstm.executeQuery();
		
		List<String> perfils = new ArrayList<String>();
		List<Double> salarios = new ArrayList<Double>();
		
		BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser = new BeanDtoGraficoSalarioUser();
		
		while(rs.next()) {
			Double media_salarial = rs.getDouble("media_salarial");
			String perfil =rs.getString("perfil");
			
			perfils.add(perfil);
			salarios.add(media_salarial);
		}
		
		beanDtoGraficoSalarioUser.setPerfils(perfils);
		beanDtoGraficoSalarioUser.setSalarios(salarios);
		
		return beanDtoGraficoSalarioUser;
	}

}
