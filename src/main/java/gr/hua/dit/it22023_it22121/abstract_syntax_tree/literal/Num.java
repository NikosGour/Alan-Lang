package gr.hua.dit.it22023_it22121.abstract_syntax_tree.literal;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;

public class Num extends Expression {
	private int value;
	
	public Num(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return Integer.toString(this.value);
	}
}