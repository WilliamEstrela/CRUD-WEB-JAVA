package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Celular;


public class DAOCelular {
	

	
	public DAOCelular() {
		
	}
	
	/**
	 * Insere um celular no banco de dados
	 * @param celular
	 * @return
	 */
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
	
	/**
	 * Busca o celular com base no id
	 * @param id
	 * @return
	 */
	public static Celular buscar(int id) {
		
		Conexao dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		String sqlProcurarCelular = "SELECT * from celular where id=?";
		
		try {
			dbConnection = Conexao.obterConexao();
			
			preparedStatement = Conexao.obterConexao().obterSQLPreparada(sqlProcurarCelular);
			String x= String.valueOf(id);
			preparedStatement.setInt(1,id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			
			Celular cell = new Celular();
			rs.next();
			cell.setId(Integer.parseInt(rs.getString("id")));
			cell.setImei(rs.getString("imei"));
			cell.setMarca(rs.getString("marca"));
			cell.setModelo(rs.getString("modelo"));
			cell.setCor(rs.getString("cor"));
			cell.setAno(rs.getString("ano"));
				
			return cell;
			
			
		}catch (SQLException e) {
			System.out.println("Erro ao procurar um celular por imei");
			System.out.println(e.getMessage());
		}
		return null;
		
		
	}
	
	/**
	 * Procura um celular com base no imei
	 * @param imei
	 * @return
	 */
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
	
	/**
	 * Lista todos os celulares
	 * @return
	 */
	public static ArrayList<Celular> buscar() {
		
		Conexao dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		String sqlProcurarCelular = "SELECT * from celular";
		
		try {
			dbConnection = Conexao.obterConexao();
			
			preparedStatement = Conexao.obterConexao().obterSQLPreparada(sqlProcurarCelular);
			
			
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

	/**
	 * Atualiza o celular com base na PK do celular
	 * @param celular
	 */
	public static void atualizar(Celular celular) {
		String sql = "UPDATE celular SET imei=?, marca=?, modelo=?, cor=?, ano=? WHERE id=?";
		
		Conexao dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		try {

			dbConnection = Conexao.obterConexao();
			
			preparedStatement = Conexao.obterConexao().obterSQLPreparada(sql);
			
			preparedStatement.setString(1, celular.getImei());
			preparedStatement.setString(2, celular.getMarca());
			preparedStatement.setString(3, celular.getModelo());
			preparedStatement.setString(4, celular.getCor());
			preparedStatement.setString(5, celular.getAno());
			preparedStatement.setInt(6, celular.getId());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar o celular");
			e.printStackTrace();
		}
	}
	
	/**
	 * Remove um celular do banco de dados
	 * @param celular
	 */
	public static void remover(Celular celular) {
		String sql = "DELETE from celular WHERE id = ?";
		
		Conexao dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		try {

			dbConnection = Conexao.obterConexao();
			
			preparedStatement = Conexao.obterConexao().obterSQLPreparada(sql);
			preparedStatement.setInt(1, celular.getId());
			
			preparedStatement.execute();
			preparedStatement.close();
			
			
		} catch (SQLException e) {
			System.out.println("Erro ao deletar o celular");
			e.printStackTrace();
		}
		
		
	}
}