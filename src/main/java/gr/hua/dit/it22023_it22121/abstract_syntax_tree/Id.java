package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.ParamSymbolEntry;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolEntry;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.ArrayType;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

public class Id extends Expression {
	private String name;
	
	public Id(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(int depth) {
		return "id(" + this.name + ")";
	}
	
	//	@Override
	public void sem(SymbolTable tbl) {
	
	}
	
	@Override
	public void gen(StringBuilder sb , int depth , SymbolTable tbl) {
		SymbolEntry symbolEntry = tbl.lookupRec(this.name);
		if (symbolEntry instanceof ParamSymbolEntry) {
			ParamSymbolEntry paramSymbolEntry = (ParamSymbolEntry) symbolEntry;
			if (paramSymbolEntry.is_refrence() && ! (paramSymbolEntry.getType() instanceof ArrayType)) {
				sb.append("*");
			}
		}
		sb.append(this.name);
	}
	
	public String getName() {
		return name;
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