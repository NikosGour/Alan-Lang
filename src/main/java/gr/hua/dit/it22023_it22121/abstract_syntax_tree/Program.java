package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

import gr.hua.dit.it22023_it22121.Utils;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Statement;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.definition.Function;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.definition.Parameter;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolEntry;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class Program {
	private Function main;
	
	public Program(Function main) {
		this.main = main;
	}
	
	public String toString(int depth) {
		return "Program(\n" + this.main.toString(depth) + ")";
	}
	
	public void sem(SymbolTable tbl) {
		Deque<Parameter> params = null;
		if (this.main.getParameters() != null) {
			params = new LinkedList<>(this.main.getParameters());
		}
		tbl.addFuncEntry(this.main.getName() , this.main.getReturn_type() , params);
		this.main.sem(tbl);
		tbl.closeScope(main.getName());
		tbl.closeScope("Global");
	}
	
	public void sem(boolean debug) {
		this.sem(new SymbolTable(main.getName() , debug));
	}
	
	public void gen(StringBuilder sb) {
		SymbolTable tbl = new SymbolTable(main.getName() , true);
		Deque<Parameter> params = null;
		if (this.main.getParameters() != null) {
			params = new LinkedList<>(this.main.getParameters());
		}
		tbl.addFuncEntry(this.main.getName() , this.main.getReturn_type() , params);
		this.main.sem(tbl);
		
		this.main.gen(sb , 1 , tbl);
		sb.append(Utils.indent(1));
		if (this.main.getName().equals("main")) {
			sb.append("_main();\n");
		}
		else {
			sb.append(this.main.getName() + "();\n");
		}
		
		tbl.closeScope(main.getName());
		tbl.closeScope("Global");
	}
}