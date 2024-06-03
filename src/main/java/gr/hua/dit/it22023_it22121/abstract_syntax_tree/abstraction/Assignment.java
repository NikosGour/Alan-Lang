package gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.ArrayAccess;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.Id;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolEntry;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.ArrayType;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

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
			
			System.out.printf("Found id %s\n" , this.id.toString(0));
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
			System.out.printf("Found array access %s\n" , a.toString(0));
			SymbolEntry symbolEntry = tbl.lookupRec(a.getName());
			Type elementType = ((ArrayType) symbolEntry.getType()).getElementType();
			this.e.sem(tbl);
			
			if (this.e instanceof ArrayAccess) {
				ArrayAccess e = (ArrayAccess) this.e;
				if (! elementType.equals(e.getType(tbl , true))) {
					throw new RuntimeException("Type mismatch in " +
					                           this.toString(0) +
					                           ", left `" +
					                           this.id.toString(0) +
					                           "`: " +
					                           elementType +
					                           " and right `" +
					                           e.toString(0) +
					                           "`: " +
					                           e.getType(tbl , true));
				}
			}
			else if (! elementType.equals(this.e.getType(tbl))) {
				throw new RuntimeException("Type mismatch in " +
				                           this.toString(0) +
				                           ", left `" +
				                           this.id.toString(0) +
				                           "`: " +
				                           elementType +
				                           " and right `" +
				                           this.e.toString(0) +
				                           "`: " +
				                           this.e.getType(tbl));
			}
			
		}
		
	}
}