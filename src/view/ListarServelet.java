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
		
		ControladorManterCelular repositoryCelular = new ControladorManterCelular();
		
		listarCelulares(request, response, repositoryCelular);
		

	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorManterCelular repositoryCelular = new ControladorManterCelular();
		
		listarCelulares(request, response, repositoryCelular);
	
		
	}


	private void listarCelulares(HttpServletRequest request, HttpServletResponse response, ControladorManterCelular repositoryCelular) throws IOException {
		
		String imeiAprocurar = new String();
		ArrayList<Celular> celulares = new ArrayList<Celular>();
		int flag = 0;
		
		if(request.getParameter("imei") != null) {
			imeiAprocurar  = request.getParameter("imei");
			flag = 1;
		}

		
		PrintWriter out = response.getWriter();
		response.setContentType( "text/html" );
		
		
		if(flag == 1) {//procurando um celular em especifico
			celulares = repositoryCelular.ListarCelular(imeiAprocurar);
		}else {//procurando todos os celulares
			celulares = repositoryCelular.ListarCelular();
			int tamanho = celulares.size();
			
		}
		
		String fileSeparator = System.getProperty("file.separator");
		String listarCelular = FileToString.convert(this.getServletContext().getRealPath(fileSeparator)+fileSeparator+"WEB-INF"+fileSeparator+"classes"+fileSeparator+"view"+fileSeparator+ "listarCelular.html");
		
		
		String nova = new String();
		
		int tamanho = celulares.size();
		
		for(int i=0; i < tamanho; i++) {
			
			String temporaria = "<td>##</td> \n";
			int id = celulares.get(i).getId();
			
			nova += "<tr>";
			nova += temporaria.replaceAll("<td>##</td>", "<td>" + i + "</td>");
			nova += temporaria.replaceAll("<td>##</td>", "<td>" + celulares.get(i).getImei() + "</td>");
			nova += temporaria.replaceAll("<td>##</td>", "<td>" + celulares.get(i).getMarca() + "</td>");
			nova += temporaria.replaceAll("<td>##</td>", "<td>" + celulares.get(i).getModelo() + "</td>");
			nova += temporaria.replaceAll("<td>##</td>", "<td>" + celulares.get(i).getCor() + "</td>");
			nova += temporaria.replaceAll("<td>##</td>", "<td>" + celulares.get(i).getAno() + "</td>");
			nova += temporaria.replaceAll("<td>##</td>", "<td>"
					+ " <button type=\"submit\" class=\"btn btn-danger btn-sm\"><span class=\"glyphicon glyphicon-minus\"></span></button>"
					+ " <a href=\"cadastro?id="+ id +"\" formmethod=\"post\" class=\"btn btn-info btn-sm\"><span class=\\\"glyphicon glyphicon-refresh\\\"></span></button>"
					+ " </td>");
			nova += "</tr>";
			
			
		}
		
		String formularioFinal = listarCelular.replaceAll("##</td>", nova);
		
		String cadastroCelular = FileToString.convert(this.getServletContext().getRealPath(fileSeparator)+fileSeparator+"WEB-INF"+fileSeparator+"classes"+fileSeparator+"view"+fileSeparator+ "cadastroCelular.html");
		String teste = new String();
		teste = formularioFinal.replaceAll("@@", cadastroCelular);
		
		if(celulares.isEmpty()) {
			
			out.print("<script>alert(\"Nada encontrado\")</script>");
		}
		
		out.print(teste);
	}

}
