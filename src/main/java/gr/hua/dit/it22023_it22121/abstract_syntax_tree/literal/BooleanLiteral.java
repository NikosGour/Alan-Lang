package gr.hua.dit.it22023_it22121.abstract_syntax_tree.literal;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Condition;

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
	public String toString() {
		return this.value.toString();
	}
}