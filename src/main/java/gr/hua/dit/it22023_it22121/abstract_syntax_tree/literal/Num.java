package gr.hua.dit.it22023_it22121.abstract_syntax_tree.literal;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.BasicType;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

public class Num extends Expression {
	private int value;
	
	public Num(int value) {
		this.value = value;
	}
	
	@Override
	public String toString(int depth) {
		return Integer.toString(this.value);
	}
	
	@Override
	public void sem(SymbolTable tbl) {
	
	}
	
	@Override
	public void gen(StringBuilder sb , int depth) {
		sb.append(this.value);
	}
	
	@Override
	public Type getType(SymbolTable tbl) {
		return BasicType.Int;
	}
}