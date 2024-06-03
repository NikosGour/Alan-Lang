package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.definition.Parameter;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.FuncSymbolEntry;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolEntry;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

import java.util.Deque;
import java.util.LinkedList;
import java.util.StringJoiner;
import java.util.concurrent.DelayQueue;

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
		if (! (symbolEntry instanceof FuncSymbolEntry)) {
			throw new IllegalArgumentException("Symbol `" + this.name + "` is not a function");
		}
		FuncSymbolEntry funcSymbolEntry = (FuncSymbolEntry) symbolEntry;
		Deque<SymbolEntry> symbol_entry_params = funcSymbolEntry.getParams();
		
		if (this.call_params != null && symbol_entry_params == null) {
			throw new IllegalArgumentException("Function `" + this.name + "` does not accept any parameters");
		}
		
		if (this.call_params == null && symbol_entry_params != null) {
			throw new IllegalArgumentException("Function `" + this.name + "` expects " + symbol_entry_params.size() + " parameters");
		}
		
		if (this.call_params != null) {
			if (this.call_params.size() != symbol_entry_params.size()) {
				Deque<Expression> call_params = new LinkedList<>(this.call_params);
				
				StringJoiner sj = new StringJoiner("|" , "{" , "}");
				for (Expression param : call_params) {
					sj.add(param.toString(0));
				}
				
				throw new IllegalArgumentException("Function `" +
				                                   this.name +
				                                   "` expects " +
				                                   symbol_entry_params.size() +
				                                   " parameters, but got " +
				                                   this.call_params.size() +
				                                   " parameters:" +
				                                   sj.toString());
			}
			
			
			int i = 0;
			LinkedList<Expression> call_params = new LinkedList<>(this.call_params);
			for (SymbolEntry param : symbol_entry_params) {
				Expression e = call_params.removeFirst();
				e.sem(tbl);
				
				Type eType;
				if (e instanceof ArrayAccess) {
					ArrayAccess arrayAccess = (ArrayAccess) e;
					eType = arrayAccess.getType(tbl , true);
				}
				else {
					eType = e.getType(tbl);
				}
				if (! param.getType().equals(eType)) {
					throw new IllegalArgumentException("Function `" +
					                                   this.name +
					                                   "` expects parameter " +
					                                   i +
					                                   " to be of type `" +
					                                   param.getType() +
					                                   "` but got `" +
					                                   eType +
					                                   "`. On call: " +
					                                   this.toString(0));
				}
				i++;
			}
		}
		
		
		//		if (this.call_params != null) {
		//			for (Expression param : this.call_params) {
		//				param.sem(tbl);
		//			}
		//		}
		
	}
	
	@Override
	public Type getType(SymbolTable tbl) {
		
		SymbolEntry symbolEntry = tbl.lookupRec(this.name);
		if (symbolEntry == null) {
			throw new IllegalArgumentException("Function `" + this.name + "` not found");
		}
		if (! (symbolEntry instanceof FuncSymbolEntry)) {
			throw new IllegalArgumentException("Symbol `" + this.name + "` is not a function");
		}
		FuncSymbolEntry funcSymbolEntry = (FuncSymbolEntry) symbolEntry;
		return funcSymbolEntry.getType();
	}
}