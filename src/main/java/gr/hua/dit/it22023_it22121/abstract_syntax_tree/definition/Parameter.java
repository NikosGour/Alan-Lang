package gr.hua.dit.it22023_it22121.abstract_syntax_tree.definition;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Definition;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

public class Parameter extends Definition {
	private String  name;
	private Type    type;
	private Boolean is_refrence;
	
	public Parameter(String name , Type type , Boolean is_refrence) {
		this.name        = name;
		this.type        = type;
		this.is_refrence = is_refrence;
	}
	
	@Override
	public String toString(int depth) {
		return "Param(" + this.name + ":" + this.type + ", refrence:" + (this.is_refrence ? "yes" : "no") + ")";
	}
	
	@Override
	public void sem(SymbolTable tbl) {
		tbl.addEntry(this.name , this.type);
		
	}
	
	@Override
	public void gen(StringBuilder sb , int depth) {
		throw new IllegalStateException("Parameter can't be a statement");
		
	}
	
	public String getName() {
		return name;
	}
	
	public Type getType() {
		return type;
	}
	
	public Boolean is_refrence() {
		return is_refrence;
	}
}