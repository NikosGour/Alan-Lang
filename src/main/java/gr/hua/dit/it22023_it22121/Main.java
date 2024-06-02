package gr.hua.dit.it22023_it22121;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.Program;
import java_cup.runtime.Symbol;

import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Reader r = new InputStreamReader(System.in);
		Lexer l = new Lexer(r);
		Parser p = new Parser(l);
		
		final boolean RUN_PARSER = true;
		if (RUN_PARSER) {
			
			Program result = (Program) p.parse().value;
			System.out.println(result.toString(0));
			result.sem();
		}
		else {
			//		 Run Lexer
			Symbol prev = null;
			while (! l.yyatEOF()) {
				try {
					Symbol next = l.next_token();
					System.out.printf("Token = %s, Value:`%s`\n" , next , next.value);
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