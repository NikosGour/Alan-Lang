package gr.hua.dit.it22023_it22121;

import java_cup.runtime.Symbol;

import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Reader r = new InputStreamReader(System.in);
		Lexer l = new Lexer(r);
		Parser p = new Parser(l);
		final Boolean RUN_PARSER = false;
		if (RUN_PARSER) {
			
			try {
				String result = (String) p.parse().value;
				System.out.println("Result = " + result);
			}
			catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		}
		else {
			//		 Run Lexer
			while (! l.yyatEOF()) {
				Symbol next = l.next_token();
				System.out.printf("Token = %s\n" , next);
			}
		}
	}
}