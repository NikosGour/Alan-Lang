package gr.hua.dit.it22023_it22121.abstract_syntax_tree.operation;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;

public class ModuloOp extends BinOp {
	public ModuloOp(Expression l , Expression r) {
		super(l , r);
	}
	
	@Override
	public String toString(int depth) {
		return this.l.toString(depth) + "%" + this.r.toString(depth);
	}
}