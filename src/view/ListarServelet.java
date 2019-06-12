package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.source_code.miniTemplator.MiniTemplator;
import controller.ControladorManterPessoa;
import model.Pessoa;

public class ListarServelet extends MiniTemplatorServelet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ControladorManterPessoa repositoryPessoa = new ControladorManterPessoa();
		
		listarPessoas(request, response, repositoryPessoa);
		

	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorManterPessoa repositoryPessoa = new ControladorManterPessoa();
		
		listarPessoas(request, response, repositoryPessoa);
	
		
	}


	private void listarPessoas(HttpServletRequest request, HttpServletResponse response, ControladorManterPessoa repositoryPessoa) throws IOException {
		
		String cpfAprocurar = new String();
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		int flag = 0;
		
		if(request.getParameter("cpf") != null) {
			cpfAprocurar  = request.getParameter("cpf");
			flag = 1;
		}

		PrintWriter out = response.getWriter();
		response.setContentType( "text/html" );
		
		if(flag == 1) {//procurando uma pessoa em especifico
			pessoas = repositoryPessoa.ListarPessoa(cpfAprocurar);
		}else {//procurando todas as pessoas
			pessoas = repositoryPessoa.ListarPessoa();
			int tamanho = pessoas.size();
			
		}
		

		MiniTemplator t = this.executaTemplate("listarPessoa.html");
		
		int tamanho = pessoas.size();
		for(int i=0; i < tamanho; i++) {
			
			t.setVariable("id", pessoas.get(i).getId());
			t.setVariable("cpf", pessoas.get(i).getCpf());
			t.setVariable("nome", pessoas.get(i).getNome());
			t.setVariable("nascimento", pessoas.get(i).getNascimento());
			t.setVariable("telefone", pessoas.get(i).getTelefone());
			t.setVariable("email", pessoas.get(i).getEmail());
			t.setVariable("cidade", pessoas.get(i).getCidade());
			t.setVariable("estado", pessoas.get(i).getEstado());
			t.setVariable("cep", pessoas.get(i).getCep());
			
			t.setVariable("carro", pessoas.get(i).getCarro());
			
			t.addBlock("manterpessoa");

		}
		if(pessoas.isEmpty()) {
			out.print("<script>alert(\"Nada encontrado\")</script>");
			response.sendRedirect("cadastro");
		}
		
		out.print(t.generateOutput());
	}

}
