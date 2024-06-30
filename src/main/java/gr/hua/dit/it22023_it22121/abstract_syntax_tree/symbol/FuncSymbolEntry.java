package gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.definition.Parameter;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

import java.util.Deque;
import java.util.List;

public class FuncSymbolEntry extends SymbolEntry {
	//	private Deque<SymbolEntry> params = null;
	//
	//	public FuncSymbolEntry(String var_name , Type t , Deque<SymbolEntry> params) {
	//		super(var_name , t);
	//		this.params = params;
	//	}
	
	private Deque<Parameter> params = null;
	
	public FuncSymbolEntry(String var_name , Type t , Deque<Parameter> params) {
		super(var_name , t);
		this.params = params;
	}
	
	public FuncSymbolEntry(String var_name , Type t) {
		super(var_name , t);
	}
	
	public Deque<Parameter> getParams() {
		return params;
	}
	
	//
	//	public Deque<SymbolEntry> getParams() {
	//		return params;
	//	}
}