package gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol;

import gr.hua.dit.it22023_it22121.Utils;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.definition.Parameter;
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
		openScope("Global");
		
		// Built-in functions
		LinkedList<Parameter> params = new LinkedList<>();
		Parameter p = new Parameter("s" , new ArrayType(BasicType.Byte) , true);
		params.add(p);
		this.addFuncEntry("writeString" , BasicType.Proc , params);
		
		this.addFuncEntry("readInteger" , BasicType.Int , null);
		
		params = new LinkedList<>();
		p      = new Parameter("n" , BasicType.Int , false);
		params.add(p);
		this.addFuncEntry("writeInteger" , BasicType.Proc , params);
		
		params = new LinkedList<>();
		p      = new Parameter("s" , new ArrayType(BasicType.Byte) , true);
		params.add(p);
		this.addFuncEntry("strlen" , BasicType.Int , params);
		
		openScope(name);
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
	
	public void addParamEntry(String sym , Type t , Boolean is_refrence) {
		if (debug) {
			System.out.printf(Utils.indent(depth) + "{ %s : %s , refrence : %s }\n" , sym , t.toString() , is_refrence);
		}
		Scope s = scopes.getFirst();
		s.addEntry(sym , new ParamSymbolEntry(sym , t , is_refrence));
	}
	
	public void addFuncEntry(String sym , Type t , Deque<Parameter> params) {
		if (debug) {
			StringJoiner sj = new StringJoiner(" , " , "[ " , " ]");
			if (params != null) {
				for (Parameter p : params) {
					sj.add(p.getName() + ":" + p.getType().toString() + " refrence:" + p.is_refrence());
				}
				System.out.printf(Utils.indent(depth) + "{ %s : %s , %s }\n" , sym , t.toString() , sj.toString());
			}
			else {
				System.out.printf(Utils.indent(depth) + "{ %s : %s }\n" , sym , t.toString());
			}
		}
		Scope s = scopes.getFirst();
		s.addEntry(sym , new FuncSymbolEntry(sym , t , params));
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
	
	public Deque<Scope> scopes;
}