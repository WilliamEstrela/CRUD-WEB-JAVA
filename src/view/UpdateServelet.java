package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.source_code.miniTemplator.MiniTemplator;
import controller.ControladorManterCelular;
import model.Celular;
import model.Marca;
import persistence.DAOMarca;
import util.FileToString;


public class UpdateServelet extends HttpServlet{
	
	private ArrayList<Marca> marcas = new ArrayList<>();
	
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
		atuaizaCelularNoBanco(request, response);
	}
	
	
	/**
	 * Metodo responsavel por receber uma requisicao e persistir os dados do celular no banco
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private static void atuaizaCelularNoBanco(HttpServletRequest request, HttpServletResponse response) throws IOException {
		  Celular celular = new Celular();
		  
		  celular.setId(Integer.valueOf(request.getParameter("id")));
		  celular.setImei(request.getParameter("imei"));
		  celular.setMarca(request.getParameter("marca"));
		  celular.setModelo(request.getParameter("modelo"));
		  celular.setCor(request.getParameter("cor"));
		  celular.setAno(request.getParameter("ano"));
		
		  ControladorManterCelular controleManterCelular = new ControladorManterCelular();
		  controleManterCelular.atualizar(celular);
		  
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
		String localArquivoCadastro = this.getServletContext().getRealPath(fileSeparator)+fileSeparator+"WEB-INF"+fileSeparator+"classes"+fileSeparator+"view"+fileSeparator+ "cadastroCelular.html"; 
		MiniTemplator t = new MiniTemplator(localArquivoCadastro);
		
		
		
		if(marcas.isEmpty()) {
			marcas = DAOMarca.obterCarros();	
		}
		
		ControladorManterCelular daoCelular = new ControladorManterCelular();
			
		Celular celular = new Celular();
		
		int idCelular;
		
		if(id != null) {//ira buscar no banco
			idCelular = Integer.parseInt(request.getParameter("id"));
			celular = daoCelular.ListarCelular(idCelular);
		}
		

		
		t.setVariable("imei", celular.getImei());
		t.setVariable("marca", celular.getMarca());
		
		int tamanho = marcas.size();
		for(int i=0; i < tamanho; i++) {
			t.setVariable("marca", marcas.get(i).getNome());
			t.addBlock("marcaBlock");
		}			
		
		
		t.setVariable("cor", celular.getCor());
		t.setVariable("ano", celular.getAno());
		
		out.println(t.generateOutput());
	}
	


}