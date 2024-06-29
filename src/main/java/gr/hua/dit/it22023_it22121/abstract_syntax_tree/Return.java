package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

import gr.hua.dit.it22023_it22121.Utils;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Statement;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;


public class Return extends Statement {
	private Expression expr;
	
	public Return(Expression expr) {
		this.expr = expr;
	}
	
	@Override
	public String toString(int depth) {
		return "return(" + this.expr.toString(depth) + ")";
	}
	
	@Override
	public void sem(SymbolTable tbl) {
		this.expr.sem(tbl);
		
	}
	
	@Override
	public void gen(StringBuilder sb , int depth) {
		sb.append("return ");
		this.expr.gen(sb , depth);
		sb.append(";\n");
		
	}
}