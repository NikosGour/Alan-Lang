package gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;

public abstract class Definition extends Statement {
	
	public abstract void sem(SymbolTable tbl);
}