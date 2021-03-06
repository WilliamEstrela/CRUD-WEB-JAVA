package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileToString {
	public static String convert(String diretorio) {
		
		StringBuilder contentBuilder = new StringBuilder();
		try {
		    BufferedReader in = new BufferedReader(new FileReader(diretorio));
		    String str;
		    while ((str = in.readLine()) != null) {
		        contentBuilder.append(str + "\n");
		    }
		    in.close();
		} catch (IOException e) {
			System.out.println("Caminho errado");
			e.printStackTrace();
		}
		String content = contentBuilder.toString();
		
		return content;
	}
	
	public static String getFileSeparator() {
		String fileSeparator = System.getProperty("file.separator");
		return fileSeparator;
	}
}
