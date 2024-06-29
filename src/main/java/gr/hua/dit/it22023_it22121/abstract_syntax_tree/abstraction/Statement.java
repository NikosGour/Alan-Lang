package gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;

public abstract class Statement extends AbstractSyntaxTree {
	public abstract String toString(int depth);
	
	public abstract void sem(SymbolTable tbl);
	
	public abstract void gen(StringBuilder sb , int depth);
	
}