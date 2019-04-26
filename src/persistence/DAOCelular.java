package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Celular;


public class DAOCelular {
	

	
	public DAOCelular() {
		
	}
	
	public static int inserir(Celular celular) {
		
		Conexao dbConnection = null;
		PreparedStatement preparedStatement = null;

		String sqlInserirCelular = "INSERT INTO celular (imei, marca, modelo, cor, ano) VALUES (?, ?, ?, ?, ?) ";
		
		try {
			dbConnection = Conexao.obterConexao();
			
			preparedStatement = Conexao.obterConexao().obterSQLPreparada(sqlInserirCelular);

			
			preparedStatement.setString(1, celular.getImei());
			preparedStatement.setString(2, celular.getMarca());
			preparedStatement.setString(3, celular.getModelo());
			preparedStatement.setString(4, celular.getCor());
			preparedStatement.setString(5, celular.getAno());
			
			preparedStatement.execute();   
        
            preparedStatement.close();
            
		}catch (SQLException e) {
			System.out.println("Erro ao inserir o celular");
			e.printStackTrace();
		}
		return 0;
	}
	
	public static ArrayList<Celular> buscar(String imei) {
		
		Conexao dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		String sqlProcurarCelular = "SELECT * from celular where imei=?";
		
		try {
			dbConnection = Conexao.obterConexao();
			
			preparedStatement = Conexao.obterConexao().obterSQLPreparada(sqlProcurarCelular);
			
			preparedStatement.setString(1, imei);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			ArrayList<Celular> celularList = new ArrayList();
			
			while(rs.next()) {
				Celular cell = new Celular();
				cell.setId(Integer.parseInt(rs.getString("id")));
				cell.setImei(rs.getString("imei"));
				cell.setMarca(rs.getString("marca"));
				cell.setModelo(rs.getString("modelo"));
				cell.setCor(rs.getString("cor"));
				cell.setAno(rs.getString("ano"));
				
				celularList.add(cell);
			}
			
			return celularList;
			
			
		}catch (SQLException e) {
			System.out.println("Erro ao procurar um celular por imei");
			System.out.println(e.getMessage());
		}
		return null;
		
		
	}
}