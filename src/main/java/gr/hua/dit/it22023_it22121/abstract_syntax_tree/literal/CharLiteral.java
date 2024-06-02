package gr.hua.dit.it22023_it22121.abstract_syntax_tree.literal;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.BasicType;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

public class CharLiteral extends Expression {
	private String value;
	
	public CharLiteral(String value) {
		this.value = value;
	}
	
	@Override
	public String toString(int depth) {
		return "'" + this.value + "'";
	}
	
	@Override
	public void sem(SymbolTable tbl) {
	
	}
	
	@Override
	public Type getType(SymbolTable tbl) {
		return BasicType.Byte;
	}
}