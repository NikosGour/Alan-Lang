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
	
	private static int     depth = 0;
	private static boolean debug = false;
	
	public SymbolTable(String name , boolean debug) {
		scopes            = new LinkedList<>();
		SymbolTable.debug = debug;
		
		// enter the initial scope
		if (debug) {
			System.out.println(" --- TYPE CHECKING  ---");
		}
		openScope(name);
		
		// Built-in functions
		Deque<SymbolEntry> writeStringParams = new LinkedList<>();
		writeStringParams.add(new SymbolEntry("str" , new ArrayType(BasicType.Byte)));
		this.addFuncEntry("writeString" , BasicType.Proc , writeStringParams);
		
		this.addFuncEntry("readInteger" , BasicType.Int , null);
		
		Deque<SymbolEntry> writeIntegerParams = new LinkedList<>();
		writeIntegerParams.add(new SymbolEntry("int" , BasicType.Int));
		this.addFuncEntry("writeInteger" , BasicType.Proc , writeIntegerParams);
		
		Deque<SymbolEntry> strlenParams = new LinkedList<>();
		strlenParams.add(new SymbolEntry("str" , new ArrayType(BasicType.Byte)));
		this.addFuncEntry("strlen" , BasicType.Int , strlenParams);
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
		if (debug) {
			System.out.printf(Utils.indent(depth) + "{ %s : %s }\n" , sym , t.toString());
		}
		Scope s = scopes.getFirst();
		s.addEntry(sym , new SymbolEntry(sym , t));
	}
	
	public void addFuncEntry(String sym , Type t , Deque<SymbolEntry> params) {
		StringJoiner sj = new StringJoiner(" , " , "[ " , " ]");
		if (params != null) {
			if (debug) {
				
				for (SymbolEntry p : params) {
					sj.add(p.getVarName() + ":" + p.getType().toString());
				}
				System.out.printf(Utils.indent(depth) + "{ %s : %s , %s }\n" , sym , t.toString() , sj.toString());
			}
			Scope s = scopes.getFirst();
			s.addEntry(sym , new FuncSymbolEntry(sym , t , params));
		}
		else {
			if (debug) {
				System.out.printf(Utils.indent(depth) + "{ %s : %s }\n" , sym , t.toString());
			}
			Scope s = scopes.getFirst();
			s.addEntry(sym , new FuncSymbolEntry(sym , t));
			
		}
	}
	
	public void openScope(String name) {
		if (debug) {
			System.out.printf(Utils.indent(depth) + "%s:\n" , name);
		}
		depth++;
		scopes.addFirst(new Scope());
	}
	
	public void closeScope(String name) {
		depth--;
		if (debug) {
			System.out.printf(Utils.indent(depth) + "END %s\n" , name);
		}
		scopes.removeFirst();
		
	}
	
	private Deque<Scope> scopes;
}