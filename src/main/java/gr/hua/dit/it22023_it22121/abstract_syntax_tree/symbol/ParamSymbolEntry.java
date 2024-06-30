package gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

import java.util.Deque;

public class ParamSymbolEntry extends SymbolEntry {
	private Boolean is_refrence;
	
	public ParamSymbolEntry(String var_name , Type t , Boolean is_refrence) {
		super(var_name , t);
		this.is_refrence = is_refrence;
	}
	
	public Boolean is_refrence() {
		return is_refrence;
	}
}