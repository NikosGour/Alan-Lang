package gr.hua.dit.it22023_it22121.abstract_syntax_tree.operation;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.BasicType;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

public class PosSign extends Expression {
	private Expression expr;
	
	public PosSign(Expression expr) {
		this.expr = expr;
	}
	
	@Override
	public String toString(int depth) {
		return "pos(" + this.expr.toString(depth) + ")";
	}
	
	@Override
	public void sem(SymbolTable tbl) {
		if (! this.expr.getType(tbl).equals(BasicType.Int)) {
			throw new RuntimeException(
					"Type mismatch in " + this.toString(0) + ", expected `" + BasicType.Int + "` but got `" + this.expr.getType(tbl) + "`");
			
		}
	}
	
	@Override
	public void gen(StringBuilder sb , int depth) {
		sb.append(" + ");
		this.expr.gen(sb , depth);
		
	}
	
	@Override
	public Type getType(SymbolTable tbl) {
		return this.expr.getType(tbl);
	}
}