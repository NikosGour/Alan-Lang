package gr.hua.dit.it22023_it22121;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.Program;
import java_cup.runtime.Symbol;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		boolean COMPILE = true;
		boolean SEMANTIC_DEBUG = false;
		boolean EXECUTE = false;
		Pattern typecheck_debug = Pattern.compile("^--typecheck-debug$");
		Pattern lexer_only = Pattern.compile("^--lexer-only$");
		Pattern execute = Pattern.compile("^-x$");
		if (args.length < 1) {
			printUsage();
			System.exit(1);
		}
		
		if (args.length > 1) {
			
			for (int i = 1; i < args.length; i++) {
				if (typecheck_debug.matcher(args[i]).matches()) {
					SEMANTIC_DEBUG = true;
					System.out.println("Typecheck debug enabled");
				}
				else if (lexer_only.matcher(args[i]).matches()) {
					COMPILE = false;
					System.out.println("Lexer only mode enabled");
				}
				else if (execute.matcher(args[i]).matches()) {
					EXECUTE = true;
					System.out.println("Execute mode enabled");
				}
				else {
					System.out.printf("Unknown option `%s`\n" , args[i]);
					printUsage();
					System.exit(1);
				}
			}
			//			if (args[1].equals("--lexer-only")) {
			//				COMPILE = false;
			//			}
			//			else if (args[1].equals("--typecheck-debug")) {
			//				SEMANTIC_DEBUG = true;
			//			}
			//			else {
			//				System.out.printf("Unknown option `%s`\n" , args[1]);
			//				printUsage();
			//				System.exit(1);
			//			}
		}
		
		Path file_name_with_path = Paths.get(args[0]);
		InputStream file = Files.newInputStream(file_name_with_path);
		Reader reader = new InputStreamReader(file);
		Lexer lexer = new Lexer(reader);
		Parser parser = new Parser(lexer);
		
		if (COMPILE) {
			
			Program result = (Program) parser.parse().value;
			System.out.println(result.toString(0));
			result.sem(SEMANTIC_DEBUG);
			
			Path file_name = file_name_with_path.getFileName();
			String file_name_no_ext = file_name.toString().substring(0 , file_name.toString().lastIndexOf('.'));
			Path output_file_c = Paths.get("alan_code_gen" , "tmp" , file_name_no_ext + ".c");
			StringBuilder output = new StringBuilder("#include \"stdlib_alan.h\"\nint main() {\n");
			result.gen(output);
			output.append(Utils.indent(1));
			output.append("return 0;\n}");
			
			Files.deleteIfExists(output_file_c);
			Files.write(output_file_c , Arrays.asList(output.toString()) , StandardCharsets.UTF_8 , StandardOpenOption.CREATE);
			
			String os = System.getProperty("os.name");
			if (! os.startsWith("Linux")) {
				System.out.println("Only Linux is supported for compilation");
				System.exit(1);
			}
			
			Path output_executable = Paths.get("alan_code_gen" , "bin" , file_name_no_ext);
			ProcessBuilder pb = new ProcessBuilder("gcc" , "-o" , output_executable.toString() , output_file_c.toString());
			pb.inheritIO();
			Process p = pb.start();
			p.waitFor();
			
			if (p.exitValue() != 0) {
				System.out.println("Compilation failed");
				System.exit(1);
			}
			
			if (EXECUTE) {
				System.out.println("Compilation successful, executing " + output_executable);
				System.out.println("---------------------------------------------");
				pb = new ProcessBuilder(output_executable.toString());
				pb.inheritIO();
				p = pb.start();
				p.waitFor();
			}
			else {
				System.out.println("Compilation successful, executable is at " + output_executable);
			}
			Files.deleteIfExists(output_file_c);
			
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
		System.out.println("Usage: java -jar <jarfile> <filename> [--lexer-only|--typecheck-debug|-x]");
	}
}