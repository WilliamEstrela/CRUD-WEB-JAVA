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
	
	/**
	 * Primeiro metodo a ser executado, ele envia o formulario de inserir ao acessar /cadastro
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			response.setContentType( "text/html" );
			
			enviarFormularioDeInserir(request, response);
			
		}
	
	/**
	 * Ao preencher o formulario de inserir o e clicar em enviar este metodo eh chamado e o celular eh persistido no banco de dados
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 	  insereCelularNoBanco(request, response);
	}

	/**
	 * Metodo responsavel por receber uma requisicao e persistir os dados do celular no banco
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private static void insereCelularNoBanco(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		
		String temppp = arquivoParaString("cadastroCelular.html");
		
		String cadastroCelular = temppp.replaceAll("<button type=\"remover\" class=\"btn btn-danger\">Remover</button>", " ");
		
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
	


}