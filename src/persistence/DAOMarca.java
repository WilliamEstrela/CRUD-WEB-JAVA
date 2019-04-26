package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Marca;

public class DAOMarca {

	public static ArrayList<Marca> obterMarcas() {
		ArrayList<Marca> marcas = new ArrayList<>();
		String sql = "SELECT * from marca";
		Conexao con = null;

		try {
			con = Conexao.obterConexao();
			ResultSet rs = con.obterSQLPreparada(sql).executeQuery();
			
			while(rs.next()) {
				Marca marca = new Marca();
				
				marca.setId(Integer.getInteger(rs.getString("id")));
				marca.setNome(rs.getString("nome"));
				
				marcas.add(marca);
				
			}
				
		}catch (SQLException e) {
			System.out.println("Erro ao procurar o marca");
			e.printStackTrace();
		}

		return marcas;
		
	}
	
}
