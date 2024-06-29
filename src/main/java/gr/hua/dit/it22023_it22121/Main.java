package gr.hua.dit.it22023_it22121;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.Program;
import java_cup.runtime.Symbol;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		boolean RUN_PARSER = true;
		boolean SEMANTIC_DEBUG = false;
		
		if (args.length < 1) {
			printUsage();
			System.exit(1);
		}
		
		if (args.length > 1) {
			
			if (args[1].equals("--lexer-only")) {
				RUN_PARSER = false;
			}
			else if (args[1].equals("--typecheck-debug")) {
				SEMANTIC_DEBUG = true;
			}
			else {
				System.out.printf("Unknown option `%s`\n" , args[1]);
				printUsage();
				System.exit(1);
			}
		}
		
		Path file_name_with_path = Paths.get(args[0]);
		InputStream file = Files.newInputStream(file_name_with_path);
		Reader reader = new InputStreamReader(file);
		Lexer lexer = new Lexer(reader);
		Parser parser = new Parser(lexer);
		
		if (RUN_PARSER) {
			
			Program result = (Program) parser.parse().value;
			System.out.println(result.toString(0));
			result.sem(SEMANTIC_DEBUG);
			
			Path file_name = file_name_with_path.getFileName();
			String file_name_no_ext = file_name.toString().substring(0 , file_name.toString().lastIndexOf('.'));
			Path output_file = Paths.get("logs" , file_name_no_ext + ".c");
			StringBuilder output = new StringBuilder("#include <stdio.h>\n#include<string.h>\n#include\"stdlib_alan.h\"\nint main() {\n");
			result.gen(output);
			output.append(Utils.indent(1));
			output.append("return 0;\n}");
			
			Files.deleteIfExists(output_file);
			Files.write(output_file , Arrays.asList(output.toString()) , StandardCharsets.UTF_8 , StandardOpenOption.CREATE);
		}
		else {
			//		 Run Lexer
			Symbol prev = null;
			while (! lexer.yyatEOF()) {
				try {
					Symbol next = lexer.next_token();
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
	
	private static void printUsage() {
		System.out.println("Usage: java -jar <jarfile> <filename> [--lexer-only|--typecheck-debug]");
	}
}