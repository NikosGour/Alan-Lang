package gr.hua.dit.it22023_it22121.abstract_syntax_tree.literal;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;

public class StringLiteral extends Expression {
	private String value;
	
	public StringLiteral(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "\"" + this.value + "\"";
	}
}