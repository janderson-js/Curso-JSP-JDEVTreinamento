package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelTelefone;



public class DAOTelefoneRepository {
	
	private Connection connection;
	private DAOUsuarioRepository daoUser = new DAOUsuarioRepository();
	
	public  DAOTelefoneRepository() {
		connection = SingleConnectionBanco.getConnection();		
	}
	
	public void gravaTelefone(ModelTelefone telefone) throws Exception {
		
		String sql = "insert into telefone (numero, usuario_pai_id, usuario_cad_id) values (?,?,?)";
		
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, telefone.getNumero());
		pstm.setLong(2, telefone.getUsuarioPaiId().getId());
		pstm.setLong(3, telefone.getUsuarioCadId().getId());
		
		pstm.execute();
		connection.commit();
			
	}
	
	public void deletarTelefone(Long id)throws Exception{

		String sql = "delete from telefone WHERE id=?";
		
		PreparedStatement pstm = connection.prepareStatement(sql);		
		pstm.setLong(1, id);
		
		pstm.executeUpdate();
		connection.commit();
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
			telefone.setUsuarioPaiId(daoUser.consultarUsuarioTelefone(rs.getLong("usuario_pai_id")));
			telefone.setUsuarioCadId(daoUser.consultarUsuarioTelefone(rs.getLong("usuario_cad_id")));
			
			telefones.add(telefone);
		}
		
		return telefones;
	}
	
	public boolean existeTelefone(String telefone, Long idUser) throws Exception {
		String sql = "Select count(1) > 0 as existe from telefone where usuario_pai_id=? and numero=? ";
		PreparedStatement pstm = connection.prepareStatement(sql);		
		pstm.setLong(1, idUser);
		pstm.setString(2, telefone);
		
		ResultSet rs = pstm.executeQuery();
		rs.next();
		
		
		return rs.getBoolean("existe");
	}
	
}
