package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexao {
	
	private static Conexao singleton;
	private Connection con;
	
	private Conexao() throws SQLException{
			con = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/celulares",
					"postgres",
					"");
	}
	public static Conexao obterConexao() throws SQLException {
		
		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {
			System.out.print("Erro ao carregar o driver");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		
		}
		
		if(singleton==null) {
			singleton = new Conexao();
		}
		return singleton;
	}
	public PreparedStatement obterSQLPreparada(String sql) throws SQLException {
		return this.con.prepareStatement(sql);
	}
	
	public void FechaConexao() {
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Nao foi possivel fechar a conexao");
			e.printStackTrace();
		}
	}
	
}