package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	
	private Connection connection;
	
	public DAOUsuarioRepository() {
		connection =  SingleConnectionBanco.getConnection();
	}
	
	public void gravarUsuario(ModelLogin modelLogin) throws Exception {
		
		String sql = "INSERT INTO model_login (login, senha, nome, email) VALUES (?,?,?,?)";
		
		PreparedStatement pstm = connection.prepareStatement(sql);
		
		pstm.setString(1, modelLogin.getLogin());
		pstm.setString(2, modelLogin.getSenha());
		pstm.setString(3, modelLogin.getNome());
		pstm.setString(4, modelLogin.getEmail());
		
		pstm.execute();
		connection.commit();
			
	}

}
