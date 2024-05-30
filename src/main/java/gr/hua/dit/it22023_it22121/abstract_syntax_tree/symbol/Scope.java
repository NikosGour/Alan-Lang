package gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol;

import java.util.HashMap;
import java.util.Map;

public class Scope {
	
	public Scope() {
		locals = new HashMap<String, SymbolEntry>();
	}
	
	public SymbolEntry lookupEntry(String sym) {
		return locals.get(sym);
	}
	
	public void addEntry(String sym , SymbolEntry entry) {
		locals.put(sym , entry);
	}
	
	private Map<String, SymbolEntry> locals;
}