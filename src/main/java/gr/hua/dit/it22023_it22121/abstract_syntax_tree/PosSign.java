package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

public class PosSign extends Expression {
	private Expression expr;
	
	public PosSign(Expression expr) {
		this.expr = expr;
	}
	
	@Override
	public String toString() {
		return "pos(" + this.expr.toString() + ")";
	}
}