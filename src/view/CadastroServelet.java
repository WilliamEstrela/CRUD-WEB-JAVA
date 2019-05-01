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
import model.Marca;
import persistence.DAOCelular;
import persistence.DAOMarca;
import util.FileToString;


public class CadastroServelet extends HttpServlet{
	
	private ArrayList<Marca> marcas = new ArrayList<>();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			response.setContentType( "text/html" );
			
			//verificando se veio um id na requisicao
			String id = request.getParameter("id");
			
			if( request.getParameter("id") != null ){
				enviarFormularioDeAlteracao(request, response, Integer.parseInt(id));
			}
			
			
			enviarFormularioDeInserir(request, response);
			
		}
		
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 	  Celular celular = new Celular();
		 	  
		 	  
		 	  celular.setImei(request.getParameter("imei"));
		 	  celular.setMarca(request.getParameter("marca"));
		 	  celular.setModelo(request.getParameter("modelo"));
		 	  celular.setCor(request.getParameter("cor"));
		 	  celular.setAno(request.getParameter("ano"));
		 	  

		 	  DAOCelular.inserir(celular);
		 	  
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
		
		if(request.getParameter("id") != null) {//se tiver algum id ele envia a alteracao
			enviarFormularioDeAlteracao(request,response,null);
		}
		
		String cadastroCelular = arquivoParaString("cadastroCelular.html");
		
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
			
		String formularioUpdate1, formularioUpdate2, formularioUpdate3, formularioUpdate4, formularioUpdate5;
		
		formularioUpdate1 = cadastroCelular.replace("{{imei}}"     , "");
		//marca
		formularioUpdate2 = formularioUpdate1.replace("{{modelo}}" , "");
		formularioUpdate3 = formularioUpdate2.replace("{{cor}}"    ,  "");
		formularioUpdate4 = formularioUpdate3.replace("{{ano}}"    ,  "");
		
		formularioUpdate5 = formularioUpdate4.replaceAll("<option>##</option>", novaLista);
		
		out.println(formularioUpdate5);
	}

	/**
	 * Recebe um nome de arquivo e retorna a string correspondente a ele
	 * @param arquivo
	 * @return
	 */
	private String arquivoParaString(String arquivo) {
		String fileSeparator = FileToString.getFileSeparator();
		String diretorio = this.getServletContext().getRealPath(fileSeparator)+fileSeparator+"WEB-INF"+fileSeparator+"classes"+fileSeparator+"view"+fileSeparator+ arquivo;
		String cadastroCelular = FileToString.convert(diretorio);
		return cadastroCelular;
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
				
		if(marcas.isEmpty()) {
			marcas = DAOMarca.obterMarcas();	
		}
		
		ControladorManterCelular daoCelular = new ControladorManterCelular();
			
		Celular celular = new Celular();
		
		int idCelular;
		
		if(id != null) {//ira buscar no banco
			idCelular = Integer.parseInt(request.getParameter("id"));
			celular = daoCelular.ListarCelular(idCelular);
		}
		
		String cadastroCelular = arquivoParaString("cadastroCelular.html");
		
		String formularioUpdate1;
		String formularioUpdate2;
		String formularioUpdate3;
		String formularioUpdate4;
		
		
		formularioUpdate1 = cadastroCelular.replace("{{imei}}"     , celular.getImei());
		//marca
		formularioUpdate2 = formularioUpdate1.replace("{{modelo}}" , celular.getModelo());
		formularioUpdate3 = formularioUpdate2.replace("{{cor}}"    ,  celular.getCor());
		formularioUpdate4 = formularioUpdate3.replace("{{ano}}"    ,  celular.getAno());
			
		out.println(formularioUpdate4);
	}

}