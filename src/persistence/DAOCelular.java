package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Celular;


public class DAOCelular {
	

	
	public DAOCelular() {
		
	}
	
	public static void inserir(Celular celular) {
		
		Conexao dbConnection = null;
		PreparedStatement preparedStatement = null;

		String sqlInserirCelular = "INSERT INTO celular (imei, marca, modelo, cor, ano, fk_proprietario) VALUES (?, ?, ?, ?, ?, null) ";
		
		try {
			dbConnection = Conexao.obterConexao();
			
			preparedStatement = Conexao.obterConexao().obterSQLPreparada(sqlInserirCelular);

			
			preparedStatement.setString(1, celular.getImei());
			preparedStatement.setString(2, celular.getMarca());
			preparedStatement.setString(3, celular.getModelo());
			preparedStatement.setString(4, celular.getCor());
			preparedStatement.setString(5, celular.getAno());
			
			preparedStatement.executeUpdate();
			
		}catch (SQLException e) {

			System.out.println(e.getMessage());
		}
	}
}