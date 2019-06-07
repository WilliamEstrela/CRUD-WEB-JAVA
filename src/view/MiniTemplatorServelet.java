package view;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import biz.source_code.miniTemplator.MiniTemplator;
import biz.source_code.miniTemplator.MiniTemplator.TemplateSyntaxException;

public abstract class MiniTemplatorServelet extends HttpServlet {
	
	
	MiniTemplator executaTemplate(String filename){
		String fileSeparator = System.getProperty("file.separator");
		String localArquivoCadastro = this.getServletContext().getRealPath(fileSeparator)+fileSeparator+"WEB-INF"+fileSeparator+"classes"+fileSeparator+"view"+fileSeparator+ filename; 
		MiniTemplator t;
		t = null;
		try {
			t = new MiniTemplator(localArquivoCadastro);
		} catch (TemplateSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
		
	}
	
	
}
