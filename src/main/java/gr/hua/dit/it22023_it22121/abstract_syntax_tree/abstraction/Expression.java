package gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

public abstract class Expression extends Statement {
	public abstract Type getType(SymbolTable tbl);
}