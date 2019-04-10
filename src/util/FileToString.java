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
		        contentBuilder.append(str);
		    }
		    in.close();
		} catch (IOException e) {
			System.out.println("Deu ruim");
			e.printStackTrace();
		}
		String content = contentBuilder.toString();
		
		return content;
	}
}
