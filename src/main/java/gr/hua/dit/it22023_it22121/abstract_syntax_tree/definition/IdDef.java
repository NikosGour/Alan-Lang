package gr.hua.dit.it22023_it22121.abstract_syntax_tree.definition;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Definition;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.ArrayType;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

public class IdDef extends Definition {
	private String name;
	private Type   type;
	
	public IdDef(String name , Type type) {
		this.name = name;
		this.type = type;
	}
	
	@Override
	public String toString(int depth) {
		return "IdDef(" + this.name + ":" + this.type + ")";
	}
	
	@Override
	public void sem(SymbolTable tbl) {
		if (this.type instanceof ArrayType) {
			ArrayType at = (ArrayType) this.type;
			tbl.addEntry(this.name , at.getType());
			return;
		}
		
		tbl.addEntry(this.name , this.type);
		
	}
	
	@Override
	public void gen(StringBuilder sb , int depth) {
		throw new IllegalStateException("IdDef can't be a statement");
	}
	
	public Type getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
}