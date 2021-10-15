package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {

	private Connection connection;
	
	public DAOLoginRepository() {
	
		connection = SingleConnectionBanco.getConnection();
		
	}
	
	public boolean validarLogin(ModelLogin modelLogin) throws Exception {
		
		String sql = "Select * from model_login where upper(login) = upper(?) and upper(senha) = upper(?)";
		
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, modelLogin.getLogin());
		pstm.setString(2, modelLogin.getSenha());
		
		ResultSet rs = pstm.executeQuery();
		
		if(rs.next()) {
			return true;
		}
		
		return false;
	}
	
}
