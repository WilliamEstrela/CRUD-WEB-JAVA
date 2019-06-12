package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Pessoa;


public class DAOPessoa {
	

	
	public DAOPessoa() {
		
	}
	
	/**
	 * Insere uma pessoa no banco de dados
	 * @param pessoa
	 * @return
	 */
	public static int inserir(Pessoa pessoa) {
		
		Conexao dbConnection = null;
		PreparedStatement preparedStatement = null;

		String sqlInserirPessoa = "INSERT INTO pessoa (cpf, nome, nascimento, telefone, email, cidade, estado, cep, carro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		try {
			dbConnection = Conexao.obterConexao();
			
			preparedStatement = Conexao.obterConexao().obterSQLPreparada(sqlInserirPessoa);

			
			preparedStatement.setString(1, pessoa.getCpf());
			preparedStatement.setString(2, pessoa.getNome());
			preparedStatement.setString(3, pessoa.getNascimento());
			preparedStatement.setString(4, pessoa.getTelefone());
			preparedStatement.setString(5, pessoa.getEmail());
			preparedStatement.setString(6, pessoa.getCidade());
			preparedStatement.setString(7, pessoa.getEstado());
			preparedStatement.setString(8, pessoa.getCep());
			
			preparedStatement.setString(9, pessoa.getCarro());
			
			preparedStatement.execute();   
            preparedStatement.close();
            
		}catch (SQLException e) {
			System.out.println("Erro ao inserir a pessoa");
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * Busca a pessoa com base no id
	 * @param id
	 * @return
	 */
	public static Pessoa buscar(int id) {
		
		Conexao dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		String sqlProcurarPessoa = "SELECT * from pessoa where id=?";
		
		try {
			dbConnection = Conexao.obterConexao();
			
			preparedStatement = Conexao.obterConexao().obterSQLPreparada(sqlProcurarPessoa);
			String x= String.valueOf(id);
			preparedStatement.setInt(1,id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			Pessoa pessoa = new Pessoa();
			rs.next();
			pessoa.setId(Integer.parseInt(rs.getString("id")));
			pessoa.setCpf(rs.getString("cpf"));
			pessoa.setNome(rs.getString("nome"));
			pessoa.setNascimento(rs.getString("nascimento"));
			pessoa.setTelefone(rs.getString("telefone"));
			pessoa.setEmail(rs.getString("email"));
			pessoa.setCidade(rs.getString("cidade"));
			pessoa.setEstado(rs.getString("estado"));
			pessoa.setCep(rs.getString("cep"));
			pessoa.setCarro(rs.getString("carro"));
			
			return pessoa;
			
			
		}catch (SQLException e) {
			System.out.println("Erro ao procurar uma pessoa por cpf");
			System.out.println(e.getMessage());
		}
		return null;
		
		
	}
	
	/**
	 * Procura uma pessoa com base no cpf
	 * @param cpf
	 * @return
	 */
	public static ArrayList<Pessoa> buscar(String cpf) {
		
		Conexao dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		String sqlProcurarPessoa = "SELECT * from pessoa where cpf=?";
		
		try {
			dbConnection = Conexao.obterConexao();
			
			preparedStatement = Conexao.obterConexao().obterSQLPreparada(sqlProcurarPessoa);
			
			preparedStatement.setString(1, cpf);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			ArrayList<Pessoa> pessoaList = new ArrayList();
			
			while(rs.next()) {
				Pessoa cell = new Pessoa();
				cell.setId(Integer.parseInt(rs.getString("id")));
				cell.setCpf(rs.getString("cpf"));
				cell.setNome(rs.getString("nome"));
				cell.setNascimento(rs.getString("nascimento"));
				cell.setTelefone(rs.getString("telefone"));
				cell.setEmail(rs.getString("email"));
				cell.setCidade(rs.getString("cidade"));
				cell.setEstado(rs.getString("estado"));
				cell.setCep(rs.getString("cep"));
				
				pessoaList.add(cell);
			}
			
			return pessoaList;
			
			
		}catch (SQLException e) {
			System.out.println("Erro ao procurar uma pessoa por cpf");
			System.out.println(e.getMessage());
		}
		return null;
		
		
	}
	
	/**
	 * Lista todas as pessoas
	 * @return
	 */
	public static ArrayList<Pessoa> buscar() {
		
		Conexao dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		String sqlProcurarPessoa = "SELECT * from pessoa";
		
		try {
			dbConnection = Conexao.obterConexao();
			
			preparedStatement = Conexao.obterConexao().obterSQLPreparada(sqlProcurarPessoa);
			
			
			ResultSet rs = preparedStatement.executeQuery();
			
			ArrayList<Pessoa> pessoaList = new ArrayList<Pessoa>();
			
			while(rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(Integer.parseInt(rs.getString("id")));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setNascimento(rs.getString("nascimento"));
				pessoa.setTelefone(rs.getString("telefone"));
				pessoa.setEmail(rs.getString("email"));
				pessoa.setCidade(rs.getString("cidade"));
				pessoa.setEstado(rs.getString("estado"));
				pessoa.setCep(rs.getString("cep"));
				
				pessoa.setCarro(rs.getString("carro"));
				
				pessoaList.add(pessoa);
			}
			
			return pessoaList;
			
			
		}catch (SQLException e) {
			System.out.println("Erro ao procurar uma pessoa por cpf");
			System.out.println(e.getMessage());
		}
		return null;
		
		
	}

	/**
	 * Atualiza a pessoa com base na PK da pessoa
	 * @param pessoa
	 */
	public static void atualizar(Pessoa pessoa) {
		String sql = "UPDATE pessoa SET nome=?, nascimento=?, telefone=?, email=?, cidade=?, estado=?, cep=?, carro=? WHERE id=?";
		
		Conexao dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		try {

			dbConnection = Conexao.obterConexao();
			
			preparedStatement = Conexao.obterConexao().obterSQLPreparada(sql);
			
			preparedStatement.setString(1, pessoa.getNome());
			preparedStatement.setString(2, pessoa.getNascimento());
			preparedStatement.setString(3, pessoa.getTelefone());
			preparedStatement.setString(4, pessoa.getEmail());
			preparedStatement.setString(5, pessoa.getCidade());
			preparedStatement.setString(6, pessoa.getEstado());
			preparedStatement.setString(7, pessoa.getCep());
			preparedStatement.setString(8, pessoa.getCarro());
			
			preparedStatement.setInt(9, pessoa.getId());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			System.out.println("Erro ao atualizar a pessoa");
			e.printStackTrace();
		}
	}
	
	/**
	 * Remove uma pessoa do banco de dados
	 * @param pessoa
	 */
	public static void remover(Pessoa pessoa) {
		String sql = "DELETE from pessoa WHERE id = ?";
		
		Conexao dbConnection = null;
		PreparedStatement preparedStatement = null;
		
		try {

			dbConnection = Conexao.obterConexao();
			
			preparedStatement = Conexao.obterConexao().obterSQLPreparada(sql);
			preparedStatement.setInt(1, pessoa.getId());
			
			preparedStatement.execute();
			preparedStatement.close();
			
			
		} catch (SQLException e) {
			System.out.println("Erro ao deletar a pessoa");
			e.printStackTrace();
		}
		
		
	}
}