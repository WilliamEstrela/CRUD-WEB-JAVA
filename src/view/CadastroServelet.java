package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.source_code.miniTemplator.MiniTemplator;
import controller.ControladorManterPessoa;
import model.Carro;
import model.Pessoa;
import persistence.DAOCarro;
import persistence.DAOPessoa;


@SuppressWarnings("serial")
public class CadastroServelet extends MiniTemplatorServelet{
	
	private ArrayList<Carro> carros = new ArrayList<>();
	
	/**
	 * Primeiro metodo a ser executado, ele envia o formulario de inserir ao acessar /cadastro
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			response.setContentType( "text/html" );
			
			String idRequest = request.getParameter("id");

			if ( idRequest == null) {//inserindo
				enviarFormularioDeInserir(request, response);
			}else {//atualizandp
				Integer id = Integer.parseInt(request.getParameter("id"));
				enviarFormularioDeAtualizar(request, response, id);
			}
		
			
		}
	
	/**
	 * Ao preencher o formulario de inserir o e clicar em enviar este metodo eh chamado e a pessoa eh persistido no banco de dados
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	  
				try {
					Integer id = Integer.parseInt(request.getParameter("id"));
					atualizaPessoaNoBanco(request, response);
				}catch(Exception e) {
					inserePessoaNoBanco(request, response);
				}
		 	  
		 	  
	}

	/**
	 * Metodo responsavel por receber uma requisicao e persistir os dados da pessoa no banco
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private static void inserePessoaNoBanco(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Pessoa pessoa = new Pessoa();

		  pessoa.setCpf(request.getParameter("cpf"));
		  pessoa.setNome(request.getParameter("nome"));
		  pessoa.setNascimento(request.getParameter("nascimento"));
		  pessoa.setTelefone(request.getParameter("telefone"));
		  pessoa.setEmail(request.getParameter("email"));
		  pessoa.setCidade(request.getParameter("cidade"));
		  pessoa.setEstado(request.getParameter("estado"));
		  pessoa.setCep(request.getParameter("cep"));
		  

		  pessoa.setCarro(request.getParameter("carro"));


		  DAOPessoa.inserir(pessoa);
		  
		  response.sendRedirect("listar");
	}
	
	/**
	 * Formulario de cadastro
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void enviarFormularioDeInserir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		if(carros.isEmpty()) {
			carros = DAOCarro.obterCarros();	
		}
		
		MiniTemplator t = this.executaTemplate("cadastroPessoa.html");
		
		int sizeMarcas = carros.size();

		Cookie[] cookies = request.getCookies();
		Cookie cookie = cookies[0];

		String nome = "William";
		Cookie c1 = new Cookie("nome", nome);		
		response.addCookie(c1);

		t.setVariable("cpf", "");
		t.setVariable("nome", "");
		t.setVariable("nascimento", "");
		t.setVariable("telefone", "");
		t.setVariable("email", "");
		t.setVariable("cidade", "");
		t.setVariable("estado", "");
		t.setVariable("cep", "");

		//criando uma lista de pessoas
		for(int i=0; i < sizeMarcas; i++) {
			t.setVariable("carro", carros.get(i).getPlaca());
			t.addBlock("carroBlock");
		}
			

		out.println(t.generateOutput());
	}



	private void enviarFormularioDeAtualizar(HttpServletRequest request, HttpServletResponse response, Integer id) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		if(carros.isEmpty()) {
			carros = DAOCarro.obterCarros();	
		}
		
		MiniTemplator t = this.executaTemplate("cadastroPessoa.html");
		
		int sizeCarros = carros.size();
		
		Pessoa pessoa = new Pessoa();
		int idPessoa;
		if(id != null) {//ira buscar no banco
			idPessoa = Integer.parseInt(request.getParameter("id"));
			ControladorManterPessoa pessoaRepo = new ControladorManterPessoa();
			
			pessoa = pessoaRepo.ListarPessoa(idPessoa);
			
			
			t.setVariable("cpf", pessoa.getCpf());
			t.setVariable("nome", pessoa.getNome());
			t.setVariable("nascimento", pessoa.getNascimento());
			t.setVariable("telefone", pessoa.getTelefone());
			t.setVariable("email", pessoa.getEmail());
			t.setVariable("cidade", pessoa.getCidade());
			t.setVariable("estado", pessoa.getEstado());
			t.setVariable("cep", pessoa.getCep());
			t.setVariable("carro", pessoa.getCarro());
			t.setVariable("id", pessoa.getId());
			
			for(int i=0; i < sizeCarros; i++) {
				t.setVariable("carro", carros.get(i).getPlaca());
				t.addBlock("carroBlock");
			}
			
			
		}

			

		out.println(t.generateOutput());
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
		  pessoa.setCarro(request.getParameter("carro"));
		
		  ControladorManterPessoa controleManterPessoa = new ControladorManterPessoa();
		  controleManterPessoa.atualizar(pessoa);
		  
		  response.sendRedirect("listar");
	}


}