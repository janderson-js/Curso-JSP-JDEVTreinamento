package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;

public class DAOVersionadorBanco implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection connection;

	public DAOVersionadorBanco() {
		connection = SingleConnectionBanco.getConnection();
	}

	public boolean arquivoSqlRodado(String nome_arquivo) throws Exception {

		String sql = "select count(1) > 0 as rodado from versionadorbanco where arquivo_sql = ?";

		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, nome_arquivo);

		ResultSet rs = pstm.executeQuery();

		rs.next();

		return rs.getBoolean("rodado");
	}

	public void gravaArquivoSqlRodado(String nome_file) throws Exception {

		String slq = "INSERT INTO versionadorbanco(arquivo_sql) VALUES (?)";

		PreparedStatement pstm = connection.prepareStatement(slq);

		pstm.setString(1, nome_file);
		pstm.execute();

	}
}
