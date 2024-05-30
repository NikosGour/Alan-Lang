package gr.hua.dit.it22023_it22121.abstract_syntax_tree.definition;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Definition;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
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
		tbl.addEntry(this.name , this.type);
		
	}
}