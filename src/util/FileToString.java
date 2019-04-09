package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileToString {
	
	private String diretorio;

	public FileToString(String diretorio) {
		this.diretorio = diretorio;
	}
	
	
	public String fileTostring() {
		
		StringBuilder contentBuilder = new StringBuilder();
		try {
		    BufferedReader in = new BufferedReader(new FileReader(diretorio));
		    String str;
		    while ((str = in.readLine()) != null) {
		        contentBuilder.append(str);
		    }
		    in.close();
		} catch (IOException e) {
		}
		String content = contentBuilder.toString();
		
		return content;
	}

	
}
