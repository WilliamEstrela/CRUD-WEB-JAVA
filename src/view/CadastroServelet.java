package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Celular;
import model.Marca;
import persistence.DAOCelular;
import persistence.DAOMarca;
import util.FileToString;


public class CadastroServelet extends HttpServlet{
	
	private ArrayList<Marca> marcas = new ArrayList<>();
	
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
		 	  

		 	  DAOCelular.inserir(celular);
		 	  response.sendRedirect("cadastro");
		      
		      PrintWriter out = response.getWriter();


	}
    
	private void enviarFormularioDeInserir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		String fileSeparator = System.getProperty("file.separator");
		String cadastroCelular = FileToString.convert(this.getServletContext().getRealPath(fileSeparator)+fileSeparator+"WEB-INF"+fileSeparator+"classes"+fileSeparator+"view"+fileSeparator+ "cadastroCelular.html");
		
		if(marcas.isEmpty()) {
			marcas = DAOMarca.obterMarcas();	
		}
		
		
		int sizeMarcas = marcas.size();
		String novaLista = new String();
		
		//criando uma lista de celulares
		for(int i=0; i < sizeMarcas; i++) {
			
			String temp = "<option selected>##</option> \n";
			novaLista += temp.replaceAll("##", marcas.get(i).getNome());
			
		}
		
		String cadastroCelularModificado = cadastroCelular.replaceAll("<option>##</option>", novaLista);
		
		out.println(cadastroCelularModificado);
	}
 


}