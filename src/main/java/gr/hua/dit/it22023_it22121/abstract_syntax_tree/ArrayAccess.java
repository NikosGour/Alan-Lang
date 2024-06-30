package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.Scope;
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
	public void gen(StringBuilder sb , int depth , SymbolTable tbl) {
		sb.append(this.name);
		sb.append("[");
		this.index.gen(sb , depth , tbl);
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
			try {
				return ((ArrayType) symbolEntry.getType()).getElementType();
			}
			catch (Exception e) {
				System.out.printf("Variable %s is not an array\n" , this.name);
				System.out.printf("Variable %s is of type %s\n" , this.name , symbolEntry.getType());
				for (Scope scope : tbl.scopes) {
					System.out.println(scope);
				}
				throw e;
			}
		}
		return symbolEntry.getType();
		
	}
	
	@Override
	public Type getType(SymbolTable tbl) {
		return this.getType(tbl , false);
	}
}