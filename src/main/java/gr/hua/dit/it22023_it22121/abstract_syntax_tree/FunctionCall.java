package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolEntry;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

import java.util.Deque;
import java.util.StringJoiner;

public class FunctionCall extends Expression {
	private String            name;
	private Deque<Expression> call_params = null;
	
	public FunctionCall(String name , Deque<Expression> call_params) {
		this.name        = name;
		this.call_params = call_params;
	}
	
	public FunctionCall(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(int depth) {
		if (call_params == null) {
			return "call(" + this.name + ")";
		}
		
		StringJoiner sj = new StringJoiner("," , "{" , "}");
		for (Expression param : this.call_params) {
			sj.add(param.toString(depth));
		}
		return "call(" + this.name + "," + sj.toString() + ")";
	}
	
	//	@Override
	public void sem(SymbolTable tbl) {
		SymbolEntry symbolEntry = tbl.lookupRec(this.name);
		if (symbolEntry == null) {
			throw new IllegalArgumentException("Function `" + this.name + "` not found");
		}
		Type return_type = symbolEntry.getType();
		
		//		if (this.call_params != null) {
		//			for (Expression param : this.call_params) {
		//				param.sem(tbl);
		//			}
		//		}
		
	}
}