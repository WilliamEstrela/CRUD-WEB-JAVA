package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.source_code.miniTemplator.MiniTemplator;
import controller.ControladorManterPessoa;
import model.Pessoa;
import model.Carro;
import persistence.DAOCarro;
import util.FileToString;


public class UpdateServelet extends HttpServlet{
	
	private ArrayList<Carro> carros = new ArrayList<>();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			response.setContentType( "text/html" );
			
			//verificando se veio um id na requisicao
			String id = request.getParameter("id");
			
			if( request.getParameter("id") != null ){
				enviarFormularioDeAlteracao(request, response, Integer.parseInt(id));
			}else {
				response.sendRedirect("cadastro");
			}
			
	
		}
		
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		atualizaPessoaNoBanco(request, response);
	}
	
	
	/**
	 * Metodo responsavel por receber uma requisicao e persistir os dados da pessoa no banco
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private static void atualizaPessoaNoBanco(HttpServletRequest request, HttpServletResponse response) throws IOException {
		  Pessoa pessoa = new Pessoa();
		  
		  pessoa.setId(Integer.valueOf(request.getParameter("id")));
		  pessoa.setCpf(request.getParameter("cpf"));
		  pessoa.setNome(request.getParameter("nome"));
		  pessoa.setNascimento(request.getParameter("nascimento"));
		  pessoa.setTelefone(request.getParameter("telefone"));
		  pessoa.setEmail(request.getParameter("email"));
		  pessoa.setCidade(request.getParameter("cidade"));
		  pessoa.setEstado(request.getParameter("estado"));
		  pessoa.setCep(request.getParameter("cep"));

		
		  ControladorManterPessoa controleManterPessoa = new ControladorManterPessoa();
		  controleManterPessoa.atualizar(pessoa);
		  
		  response.sendRedirect("listar");
	}
	
	/**
	 * Formulario de alteracao
	 * @param request
	 * @param response
	 * @param id
	 * @throws IOException
	 */
	private void enviarFormularioDeAlteracao(HttpServletRequest request, HttpServletResponse response, Integer id) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		String fileSeparator = System.getProperty("file.separator");
		String localArquivoCadastro = this.getServletContext().getRealPath(fileSeparator)+fileSeparator+"WEB-INF"+fileSeparator+"classes"+fileSeparator+"view"+fileSeparator+ "cadastroPessoa.html"; 
		MiniTemplator t = new MiniTemplator(localArquivoCadastro);
		
		
		
		if(carros.isEmpty()) {
			carros = DAOCarro.obterCarros();	
		}
		
		ControladorManterPessoa daoPessoa = new ControladorManterPessoa();
			
		Pessoa pessoa = new Pessoa();
		
		int idPessoa;
		
		if(id != null) {//ira buscar no banco
			idPessoa = Integer.parseInt(request.getParameter("id"));
			pessoa = daoPessoa.ListarPessoa(idPessoa);
		}
		

		
		t.setVariable("cpf", pessoa.getCpf());
		t.setVariable("nome", pessoa.getNome());
		t.setVariable("nascimento", pessoa.getNascimento());

		
	//	int tamanho = marcas.size();
	//	for(int i=0; i < tamanho; i++) {
	//		t.setVariable("marca", marcas.get(i).getNome());
	//		t.addBlock("marcaBlock");
	//	}			
		
		
		t.setVariable("telefone", pessoa.getTelefone());
		t.setVariable("email", pessoa.getEmail());
		t.setVariable("cidade", pessoa.getCidade());
		t.setVariable("estado", pessoa.getEstado());
		t.setVariable("cep", pessoa.getCep());

		
		out.println(t.generateOutput());
	}
	


}