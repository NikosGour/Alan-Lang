package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;

public class Id extends Expression {
	private String name;
	
	public Id(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(int depth) {
		return "id(" + this.name + ")";
	}
	
	//	@Override
	public void sem(SymbolTable tbl) {
	
	}
	
	public String getName() {
		return name;
	}
}