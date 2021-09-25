package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection;

	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}

	public ModelLogin gravarUsuario(ModelLogin modelLogin, Long userlogado) throws Exception {

		if (modelLogin.isNovo()) {
			String sql = "INSERT INTO model_login (login, senha, nome, email, usuario_id) VALUES (?,?,?,?,?)";

			PreparedStatement pstm = connection.prepareStatement(sql);

			pstm.setString(1, modelLogin.getLogin());
			pstm.setString(2, modelLogin.getSenha());
			pstm.setString(3, modelLogin.getNome());
			pstm.setString(4, modelLogin.getEmail());
			pstm.setLong(5, userlogado);
			pstm.execute();
			connection.commit();
		} else {
			String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=? WHERE id = ?";

			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, modelLogin.getLogin());
			pstm.setString(2, modelLogin.getSenha());
			pstm.setString(3, modelLogin.getNome());
			pstm.setString(4, modelLogin.getEmail());
			pstm.setLong(5, modelLogin.getId());

			pstm.executeUpdate();
			connection.commit();

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

		}
		connection.commit();

		return modelLogin;

	}
	
	public ModelLogin consultarUsuario(String login) throws Exception {
		ModelLogin modelLogin = new ModelLogin();
		String sql = "Select * from model_login WHERE upper(login)=upper(?) AND useradmin is false";

		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, login);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {

			modelLogin.setId(rs.getLong("id"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setEmail(rs.getString("email"));

		}
		connection.commit();

		return modelLogin;

	}

	public ModelLogin consultarUsuario(String login, Long userLogado) throws Exception {
		ModelLogin modelLogin = new ModelLogin();
		String sql = "Select * from model_login WHERE upper(login)=upper(?) AND useradmin is false AND usuario_id=?";

		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, login);
		pstm.setLong(2, userLogado);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {

			modelLogin.setId(rs.getLong("id"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setEmail(rs.getString("email"));

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
			modelLogin.setEmail(rs.getString("email"));

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

	public List<ModelLogin> buscarUser(String nome, Long userLogado) throws Exception {

		List<ModelLogin> user = new ArrayList<>();

		String sql = "select * from model_login where upper(nome) like upper(?) AND useradmin is false AND usuario_id = ?";

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

	public List<ModelLogin> listarUsers(Long userLogado) throws Exception {

		List<ModelLogin> user = new ArrayList<>();

		String sql = "select * from model_login Where usuario_id=? AND useradmin is false ORDER BY id ASC";

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

}
