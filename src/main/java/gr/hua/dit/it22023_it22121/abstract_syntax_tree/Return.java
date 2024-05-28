package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

public class Return extends Statement {
	private Expression expr;
	
	public Return(Expression expr) {
		this.expr = expr;
	}
	
	@Override
	public String toString() {
		return "return(" + this.expr.toString() + ")";
	}
}