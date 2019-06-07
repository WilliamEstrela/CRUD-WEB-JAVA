package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.source_code.miniTemplator.MiniTemplator;
import controller.ControladorManterCelular;
import model.Celular;

public class ListarServelet extends MiniTemplatorServelet{
	
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
		

		MiniTemplator t = this.executaTemplate("listarCelular.html");
		
		int tamanho = celulares.size();
		for(int i=0; i < tamanho; i++) {

			t.setVariable("indice", i);
			t.setVariable("imei", celulares.get(i).getImei());
			t.setVariable("marca", celulares.get(i).getMarca());
			t.setVariable("modelo", celulares.get(i).getModelo());
			t.setVariable("cor", celulares.get(i).getCor());
			t.setVariable("ano", celulares.get(i).getAno());
			t.setVariable("id", celulares.get(i).getId());
			
			t.addBlock("mantercelular");

		}
		
		if(celulares.isEmpty()) {
			out.print("<script>alert(\"Nada encontrado\")</script>");
			response.sendRedirect("cadastro");
		}
		
		out.print(t.generateOutput());
	}

}
