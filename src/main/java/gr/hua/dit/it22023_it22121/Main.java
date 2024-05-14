package gr.hua.dit.it22023_it22121;

import java_cup.runtime.Symbol;

import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Reader r = new InputStreamReader(System.in);
		Lexer l = new Lexer(r);
		Parser p = new Parser(l);
		
		final boolean RUN_PARSER = true;
		if (RUN_PARSER) {
			
			try {
				String result = (String) p.parse().value;
				System.out.println("Result = " + result);
			}
			catch (Exception e) {
				System.err.println("Our Error: " + e.getMessage());
			}
		}
		else {
			//		 Run Lexer
			Symbol prev = null;
			while (! l.yyatEOF()) {
				try {
					Symbol next = l.next_token();
					System.out.printf("Token = %s\n" , next);
					prev = next;
				}
				catch (Error e) {
					System.out.printf("Error at line:%d, column:%d\n" , prev.left , prev.right);
					throw e;
				}
			}
		}
	}
}