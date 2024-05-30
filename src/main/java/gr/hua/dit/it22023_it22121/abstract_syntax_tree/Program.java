package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Statement;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.definition.Function;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;

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
		this.main.sem(tbl);
		tbl.closeScope(main.getName());
	}
	
	public void sem() {
		this.sem(new SymbolTable(main.getName()));
	}
}