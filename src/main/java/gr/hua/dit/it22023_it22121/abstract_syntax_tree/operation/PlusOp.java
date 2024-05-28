package gr.hua.dit.it22023_it22121.abstract_syntax_tree.operation;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;

public class PlusOp extends BinOp {
	public PlusOp(Expression l , Expression r) {
		super(l , r);
	}
	
	@Override
	public String toString() {
		return this.l + "+" + this.r;
	}
}