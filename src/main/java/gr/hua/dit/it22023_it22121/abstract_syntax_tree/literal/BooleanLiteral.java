package gr.hua.dit.it22023_it22121.abstract_syntax_tree.literal;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Condition;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.BasicType;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

public class BooleanLiteral extends Condition {
	
	private enum Value {
		TRUE, FALSE
	}
	
	public static final BooleanLiteral TRUE  = new BooleanLiteral(Value.TRUE);
	public static final BooleanLiteral FALSE = new BooleanLiteral(Value.FALSE);
	
	private Value value;
	
	private BooleanLiteral(Value value) {
		this.value = value;
	}
	
	@Override
	public String toString(int depth) {
		return this.value.toString();
	}
	
	@Override
	public void sem(SymbolTable tbl) {
	
	}
	
	@Override
	public Type getType(SymbolTable tbl) {
		return BasicType.Byte;
	}
}