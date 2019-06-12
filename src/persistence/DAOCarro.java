package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Carro;

public class DAOCarro {

	public static ArrayList<Carro> obterCarros() {
		ArrayList<Carro> carros = new ArrayList<>();
		String sql = "SELECT * from carro";
		Conexao con = null;

		try {
			con = Conexao.obterConexao();
			ResultSet rs = con.obterSQLPreparada(sql).executeQuery();
			
			while(rs.next()) {
				Carro carro = new Carro();
				
				carro.setId(Integer.getInteger(rs.getString("id")));
				carro.setPlaca(rs.getString("placa"));
				carro.setMarca(rs.getString("marca"));
				carro.setModelo(rs.getString("modelo"));
				carro.setAno(rs.getInt("ano"));


				
				carros.add(carro);
				
			}
				
		}catch (SQLException e) {
			System.out.println("Erro ao procurar o carro");
			e.printStackTrace();
		}

		return carros;
		
	}
	
}
