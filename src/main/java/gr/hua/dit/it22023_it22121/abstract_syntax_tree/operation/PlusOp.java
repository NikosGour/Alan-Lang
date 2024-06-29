package gr.hua.dit.it22023_it22121.abstract_syntax_tree.operation;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

public class PlusOp extends BinOp {
	public PlusOp(Expression l , Expression r) {
		super(l , r);
	}
	
	@Override
	public String toString(int depth) {
		return this.l.toString(depth) + "+" + this.r.toString(depth);
	}
	
	@Override
	public void sem(SymbolTable tbl) {
		
		this.l.sem(tbl);
		this.r.sem(tbl);
		if (! this.l.getType(tbl).equals(this.r.getType(tbl))) {
			throw new RuntimeException("Type mismatch in " +
			                           this.toString(0) +
			                           ", left `" +
			                           this.l.toString(0) +
			                           "`: " +
			                           this.l.getType(tbl) +
			                           " and right `" +
			                           this.r.toString(0) +
			                           "`: " +
			                           this.r.getType(tbl));
		}
	}
	
	@Override
	public void gen(StringBuilder sb , int depth) {
		this.l.gen(sb , depth);
		sb.append(" + ");
		this.r.gen(sb , depth);
	}
	
	@Override
	public Type getType(SymbolTable tbl) {
		
		return this.l.getType(tbl);
		
	}
}