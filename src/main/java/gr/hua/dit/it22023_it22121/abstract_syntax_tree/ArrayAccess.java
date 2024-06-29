package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolEntry;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.ArrayType;

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
	
	@Override
	public void gen(StringBuilder sb , int depth) {
		sb.append(this.name);
		sb.append("[");
		this.index.gen(sb , depth);
		sb.append("]");
		
	}
	
	public String getName() {
		return name;
	}
	
	public Expression getIndex() {
		return index;
	}
	
	public Type getType(SymbolTable tbl , boolean get_element_type) {
		SymbolEntry symbolEntry = tbl.lookupRec(this.name);
		if (symbolEntry == null) {
			throw new RuntimeException("Variable " + this.name + " not declared");
		}
		if (get_element_type) {
			return ((ArrayType) symbolEntry.getType()).getElementType();
		}
		return symbolEntry.getType();
		
	}
	
	@Override
	public Type getType(SymbolTable tbl) {
		return this.getType(tbl , false);
	}
}