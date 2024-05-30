package gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

public class SymbolEntry {
	private String var_name;
	private Type   t;
	
	public SymbolEntry(String var_name , Type t) {
		this.var_name = var_name;
		this.t        = t;
	}
	
	public Type getType() {
		return t;
	}
	
	public String getVarName() {
		return var_name;
	}
}