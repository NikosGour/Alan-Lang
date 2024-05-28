package gr.hua.dit.it22023_it22121.abstract_syntax_tree.operation;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;

public abstract class BinOp extends Expression {
	protected Expression l, r;
	
	public BinOp(Expression l , Expression r) {
		this.l = l;
		this.r = r;
	}
	
	public abstract String toString(int depth);
}