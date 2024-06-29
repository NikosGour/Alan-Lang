package gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction;

import gr.hua.dit.it22023_it22121.Utils;
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
			ArrayAccess id_array = (ArrayAccess) id;
			return id_array.getName() + "[" + id_array.getIndex().toString(depth) + "] = " + e.toString(depth);
			
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
			
			Type eType;
			if (this.e instanceof ArrayAccess) {
				ArrayAccess e = (ArrayAccess) this.e;
				eType = e.getType(tbl , true);
			}
			else {
				eType = this.e.getType(tbl);
			}
			if (! symbolEntry.getType().equals(eType)) {
				throw new RuntimeException("Type mismatch in " +
				                           this.toString(0) +
				                           ", left `" +
				                           this.id.toString(0) +
				                           "`: " +
				                           symbolEntry.getType() +
				                           " and right `" +
				                           this.e.toString(0) +
				                           "`: " +
				                           eType);
			}
		}
		else {
			ArrayAccess id_array = (ArrayAccess) this.id;
			SymbolEntry symbolEntry = tbl.lookupRec(id_array.getName());
			Type elementType = ((ArrayType) symbolEntry.getType()).getElementType();
			this.e.sem(tbl);
			
			Type eType;
			if (this.e instanceof ArrayAccess) {
				ArrayAccess e = (ArrayAccess) this.e;
				eType = e.getType(tbl , true);
			}
			else {
				eType = this.e.getType(tbl);
			}
			
			if (! elementType.equals(eType)) {
				throw new RuntimeException("Type mismatch in " +
				                           this.toString(0) +
				                           ", left `" +
				                           this.id.toString(0) +
				                           "`: " +
				                           elementType +
				                           " and right `" +
				                           this.e.toString(0) +
				                           "`: " +
				                           eType);
			}
			
		}
		
	}
	
	@Override
	public void gen(StringBuilder sb , int depth) {
		if (id instanceof ArrayAccess) {
			ArrayAccess id_array = (ArrayAccess) id;
			sb.append(id_array.getName());
			sb.append("[");
			id_array.getIndex().gen(sb , depth);
			sb.append("]");
		}
		else {
			sb.append(((Id) id).getName());
		}
		sb.append(" = ");
		e.gen(sb , depth);
		sb.append(";");
		
	}
}