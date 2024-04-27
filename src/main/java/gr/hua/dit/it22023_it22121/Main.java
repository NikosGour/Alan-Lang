package gr.hua.dit.it22023_it22121;

import java.io.*;

public class Main {
	
	public static void main(String[] args) {
		Reader r = new InputStreamReader(System.in);
		Lexer l = new Lexer(r);
		Parser p = new Parser(l);
		
		try {
			Integer result = (Integer) p.parse().value;
			System.out.println("Result = " + result);
		}
		catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}