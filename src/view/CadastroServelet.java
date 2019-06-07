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
import persistence.DAOCelular;
import persistence.DAOMarca;


@SuppressWarnings("serial")
public class CadastroServelet extends MiniTemplatorServelet{
	
	private ArrayList<Marca> marcas = new ArrayList<>();
	
	/**
	 * Primeiro metodo a ser executado, ele envia o formulario de inserir ao acessar /cadastro
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			response.setContentType( "text/html" );
			
			String idRequest = request.getParameter("id");

			if ( idRequest == null) {
				enviarFormularioDeInserir(request, response);
			}else {
				Integer id = Integer.parseInt(request.getParameter("id"));
				enviarFormularioDeAtualizar(request, response, id);
			}
		
			
		}
	
	/**
	 * Ao preencher o formulario de inserir o e clicar em enviar este metodo eh chamado e o celular eh persistido no banco de dados
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 	  insereCelularNoBanco(request, response);
		 	  
				try {
					Integer id = Integer.parseInt(request.getParameter("id"));
					atuaizaCelularNoBanco(request, response);
				}catch(Exception e) {
					enviarFormularioDeInserir(request, response);
				}
		 	  
		 	  
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
		
		if(marcas.isEmpty()) {
			marcas = DAOMarca.obterMarcas();	
		}
		
		MiniTemplator t = this.executaTemplate("cadastroCelular.html");
		
		int sizeMarcas = marcas.size();
		

		t.setVariable("imei", "");
		t.setVariable("modelo", "");
		t.setVariable("cor", "");

		//criando uma lista de celulares
		for(int i=0; i < sizeMarcas; i++) {
			t.setVariable("marca", marcas.get(i).getNome());
			t.addBlock("marcaBlock");
		}
			

		out.println(t.generateOutput());
	}

	private void enviarFormularioDeAtualizar(HttpServletRequest request, HttpServletResponse response, Integer id) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		if(marcas.isEmpty()) {
			marcas = DAOMarca.obterMarcas();	
		}
		
		String fileSeparator = System.getProperty("file.separator");
		String localArquivoCadastro = this.getServletContext().getRealPath(fileSeparator)+fileSeparator+"WEB-INF"+fileSeparator+"classes"+fileSeparator+"view"+fileSeparator+ "cadastroCelular.html"; 
		MiniTemplator t = new MiniTemplator(localArquivoCadastro);
		
		int sizeMarcas = marcas.size();
		
		Celular celular = new Celular();
		int idCelular;
		if(id != null) {//ira buscar no banco
			idCelular = Integer.parseInt(request.getParameter("id"));
			ControladorManterCelular celularRepo = new ControladorManterCelular();
			celular = celularRepo.ListarCelular(idCelular);
			
			
			t.setVariable("imei", celular.getImei());
			t.setVariable("modelo", celular.getModelo());
			t.setVariable("cor", celular.getCor());
			t.setVariable("ano", celular.getAno());
			
			for(int i=0; i < sizeMarcas; i++) {
				t.setVariable("marca", marcas.get(i).getNome());
				t.addBlock("marcaBlock");
			}
			
			
		}

			

		out.println(t.generateOutput());
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


}