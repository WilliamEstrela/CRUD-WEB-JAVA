package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ControladorManterCelular;
import model.Celular;
import util.FileToString;

public class ListarServelet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text/html" );
		
		PrintWriter out = response.getWriter();
		String listarCelular = FileToString.convert("/Users/williamestrela/eclipse-workspace/Celulares/src/view/listarCelular.html");
		out.print(listarCelular);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorManterCelular repositoryCelular = new ControladorManterCelular();
		String imeiAprocurar  = request.getParameter("imei");
		
		PrintWriter out = response.getWriter();
		response.setContentType( "text/html" );
		
		ArrayList<Celular> celulares = repositoryCelular.ListarCelular(imeiAprocurar);
		
		if(celulares.isEmpty()) {
			out.print("Nada foi encontrado");
		}
		
		for(Celular celular : celulares){
		    System.out.println (celular.getId());
		    System.out.println (celular.getImei());
		    System.out.println (celular.getMarca());
		    System.out.println (celular.getModelo());
		    System.out.println (celular.getAno());
		}
		
	}

}
