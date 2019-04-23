package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ControladorManterCelular;
import model.Celular;
import model.Proprietario;
import util.FileToString;

public class CadastroServelet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType( "text/html" );
		
		enviarFormularioDeInserir(request, response);

		PrintWriter out = response.getWriter();
		
	}
	
	
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 	  Celular celular = new Celular();
		 	  
		 	  celular.setImei(request.getParameter("imei"));
		 	  celular.setMarca(request.getParameter("marca"));
		 	  celular.setModelo(request.getParameter("modelo"));
		 	  celular.setCor(request.getParameter("cor"));
		 	  celular.setAno(request.getParameter("ano"));
		 	  celular.setProprietario(1);
		 	  
		 	  
		 	  ControladorManterCelular repositoryCelular = new ControladorManterCelular();
		 	  repositoryCelular.incluirCelular(celular);
		 	  
		 	  //procurar um celular ee retorna o id dele com base no imei
		 	  ArrayList<Celular> celularID = repositoryCelular.ListarCelular(celular.getImei());
		 	  celular.setId(celularID.get(0).getId());
		 	  
		 	  
		 	  //tem que ir no banco, buscar o id do celular e setar no celular para depois vincular ele
		 	  //com proprietarios
		 	  HttpSession session = request.getSession(true);
		 	  session.setAttribute("celular", celular);
		 	  
		 	  Proprietario proprietario = new Proprietario();
		 	  
		 	  proprietario.setCpf(request.getParameter("cpf"));
		   	  proprietario.setNome(request.getParameter("nome"));
		      proprietario.setTelefone(request.getParameter("telefone"));

		 	  
//		 	  ControladorManterProprietario repositoryProprietario = new ControladorManterProprietario();
		 	  
		      
		      PrintWriter out = response.getWriter();
		      out.println("Inserindo...");
	}
    
	private void enviarFormularioDeInserir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		String cadastroCelular = FileToString.convert("/Users/williamestrela/eclipse-workspace/Celulares/src/view/cadastroCelular.html");
		String cadastroProprietario = FileToString.convert("/Users/williamestrela/eclipse-workspace/Celulares/src/view/cadastroProprietario.html");

		out.println(cadastroCelular);
		out.println(cadastroProprietario);
	}
 


}