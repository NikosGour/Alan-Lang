package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolEntry;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

public class ArrayAccess extends Expression {
	private String     name;
	private Expression index;
	
	public ArrayAccess(String name , Expression index) {
		this.name  = name;
		this.index = index;
	}
	
	@Override
	public String toString(int depth) {
		return this.name + "[" + this.index.toString(depth) + "]";
	}
	
	@Override
	public void sem(SymbolTable tbl) {
	
	}
	
	public String getName() {
		return name;
	}
	
	public Expression getIndex() {
		return index;
	}
	
	@Override
	public Type getType(SymbolTable tbl) {
		SymbolEntry symbolEntry = tbl.lookupRec(this.name);
		if (symbolEntry == null) {
			throw new RuntimeException("Variable " + this.name + " not declared");
		}
		return symbolEntry.getType();
		
	}
}