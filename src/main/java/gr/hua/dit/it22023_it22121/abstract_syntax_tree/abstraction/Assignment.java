package gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.ArrayAccess;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.Id;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolEntry;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;

public class Assignment extends Statement {
	private Expression id;
	private Expression e;
	
	public Assignment(Expression id , Expression e) {
		this.id = id;
		this.e  = e;
	}
	
	@Override
	public String toString(int depth) {
		if (id instanceof ArrayAccess) {
			ArrayAccess a = (ArrayAccess) id;
			return a.getName() + "[" + a.getIndex().toString(depth) + "] = " + e.toString(depth);
			
		}
		return "Assignment(" + id.toString(depth) + " = " + e.toString(depth) + ")";
	}
	
	@Override
	public void sem(SymbolTable tbl) {
		if (this.id instanceof Id) {
			Id id = (Id) this.id;
			SymbolEntry symbolEntry = tbl.lookupRec(id.getName());
			if (symbolEntry == null) {
				throw new RuntimeException("Variable " + id.getName() + " not declared");
			}
			this.e.sem(tbl);
			if (! symbolEntry.getType().equals(this.e.getType(tbl))) {
				throw new RuntimeException("Type mismatch in " +
				                           this.toString(0) +
				                           ", left `" +
				                           this.id.toString(0) +
				                           "`: " +
				                           symbolEntry.getType() +
				                           " and right `" +
				                           this.e.toString(0) +
				                           "`: " +
				                           this.e.getType(tbl));
			}
		}
		else {
			ArrayAccess a = (ArrayAccess) this.id;
			SymbolEntry symbolEntry = tbl.lookupRec(a.getName());
			this.e.sem(tbl);
			if (! symbolEntry.getType().equals(this.e.getType(tbl))) {
				throw new RuntimeException("Type mismatch in " +
				                           this.toString(0) +
				                           ", left `" +
				                           this.id.toString(0) +
				                           "`: " +
				                           symbolEntry.getType() +
				                           " and right `" +
				                           this.e.toString(0) +
				                           "`: " +
				                           this.e.getType(tbl));
			}
			
		}
		
	}
}