package gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol;

import gr.hua.dit.it22023_it22121.Utils;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.ArrayType;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.BasicType;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class SymbolTable {
	
	private static int depth = 0;
	
	public SymbolTable(String name) {
		scopes = new LinkedList<>();
		
		// enter the initial scope
		openScope(name);
		Deque<SymbolEntry> writeStringParams = new LinkedList<>();
		writeStringParams.add(new SymbolEntry("str" , new ArrayType(BasicType.Byte)));
		this.addFuncEntry("writeString" , BasicType.Proc , writeStringParams);
		
		this.addFuncEntry("readInteger" , BasicType.Int , null);
	}
	
	public SymbolEntry lookup(String sym) {
		Scope s = scopes.getFirst();
		return s.lookupEntry(sym);
	}
	
	// recurse through all scopes
	public SymbolEntry lookupRec(String sym) {
		// first scope in the list in the most recent
		for (Scope s : scopes) {
			SymbolEntry e = s.lookupEntry(sym);
			if (e != null) return e;
		}
		return null;
	}
	
	public void addEntry(String sym , Type t) {
		System.out.printf(Utils.indent(depth) + "{ %s : %s }\n" , sym , t.toString());
		Scope s = scopes.getFirst();
		s.addEntry(sym , new SymbolEntry(sym , t));
	}
	
	public void addFuncEntry(String sym , Type t , Deque<SymbolEntry> params) {
		StringJoiner sj = new StringJoiner(" , " , "[ " , " ]");
		if (params != null) {
			
			for (SymbolEntry p : params) {
				sj.add(p.getVarName() + ":" + p.getType().toString());
			}
			System.out.printf(Utils.indent(depth) + "{ %s : %s , %s }\n" , sym , t.toString() , sj.toString());
			Scope s = scopes.getFirst();
			s.addEntry(sym , new FuncSymbolEntry(sym , t , params));
		}
		else {
			System.out.printf(Utils.indent(depth) + "{ %s : %s }\n" , sym , t.toString());
			Scope s = scopes.getFirst();
			s.addEntry(sym , new FuncSymbolEntry(sym , t));
			
		}
	}
	
	public void openScope(String name) {
		System.out.printf(Utils.indent(depth) + "%s:\n" , name);
		depth++;
		scopes.addFirst(new Scope());
	}
	
	public void closeScope(String name) {
		depth--;
		System.out.printf(Utils.indent(depth) + "END %s\n" , name);
		scopes.removeFirst();
		
	}
	
	private Deque<Scope> scopes;
}