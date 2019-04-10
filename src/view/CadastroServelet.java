package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.FileToString;

public class CadastroServelet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text/html" );
		
		enviarFormularioDeInserir(request, response);

		PrintWriter out = response.getWriter();
		
	}
	
	
    
	private void enviarFormularioDeInserir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		String cadastroCelular = FileToString.convert("Users/williamestrela/eclipse-workspace/Celulares/src/view/cadastroCelular.html");
		String cadastroProprietario = FileToString.convert("Users/williamestrela/eclipse-workspace/Celulares/src/view/cadastroCelular.html");
		
		out.println(cadastroCelular);
		out.println(cadastroProprietario);
	}
 


}