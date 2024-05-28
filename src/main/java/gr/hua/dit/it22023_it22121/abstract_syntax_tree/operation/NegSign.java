package gr.hua.dit.it22023_it22121.abstract_syntax_tree.operation;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;

public class NegSign extends Expression {
	private Expression expr;
	
	public NegSign(Expression expr) {
		this.expr = expr;
	}
	
	@Override
	public String toString() {
		return "neg(" + this.expr.toString() + ")";
	}
}