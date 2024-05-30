package gr.hua.dit.it22023_it22121.abstract_syntax_tree.definition;

import gr.hua.dit.it22023_it22121.Utils;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Definition;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Statement;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolEntry;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

import java.util.*;


public class Function extends Definition {
	private String            name;
	private Type              return_type;
	private Deque<Parameter>  parameters;
	private Deque<Definition> local_defs;
	private Deque<Statement>  statements;
	
	public Function(String name , Type return_type , Deque<Definition> local_defs , Deque<Statement> statements) {
		this.name        = name;
		this.return_type = return_type;
		this.local_defs  = local_defs;
		this.statements  = statements;
	}
	
	public Function(String name , Type return_type , Deque<Definition> local_defs , Deque<Statement> statements ,
	                Deque<Parameter> parameters) {
		Set<String> params = new HashSet<>();
		for (Parameter parameter : parameters) {
			if (params.contains(parameter.getName())) {
				throw new IllegalArgumentException(
						String.format("Duplicate Parameter name: `%s`\non Function: `%s`" , parameter.getName() , name));
			}
			params.add(parameter.getName());
		}
		
		this.name        = name;
		this.return_type = return_type;
		this.statements  = statements;
		this.local_defs  = local_defs;
		this.parameters  = parameters;
	}
	
	@Override
	public String toString(int depth) {
		
		
		StringJoiner params_sj = new StringJoiner("\n" , "params{" , Utils.indent(depth) + "}");
		if (this.parameters != null) {
			params_sj.add("");
			for (Statement param : this.parameters) {
				params_sj.add(Utils.indent(depth + 1) + param.toString(depth + 1));
			}
			params_sj.add("");
		}
		StringJoiner local_defs_sj;
		if (! this.local_defs.isEmpty()) {
			local_defs_sj = new StringJoiner("\n" , "local_defs{\n" , "\n" + Utils.indent(depth) + "}");
		}
		else {
			local_defs_sj = new StringJoiner("\n" , "local_defs{" , "}");
		}
		for (Statement local_def : this.local_defs) {
			local_defs_sj.add(Utils.indent(depth + 1) + local_def.toString(depth + 1));
		}
		
		StringJoiner statements_sj;
		if (! this.statements.isEmpty()) {
			statements_sj = new StringJoiner("\n" , this.name + "_body{\n" , "\n" + Utils.indent(depth) + "}");
		}
		else {
			statements_sj = new StringJoiner("\n" , "{" , "}");
		}
		for (Statement statement : this.statements) {
			statements_sj.add(Utils.indent(depth + 1) + statement.toString(depth + 1));
		}
		return "Func(" +
		       this.name +
		       ":" +
		       this.return_type +
		       ")" +
		       "\n" +
		       Utils.indent(depth) +
		       params_sj.toString() +
		       "\n" +
		       Utils.indent(depth) +
		       local_defs_sj.toString() +
		       "\n" +
		       Utils.indent(depth) +
		       statements_sj.toString();
	}
	
	public void sem(SymbolTable tbl) {
		if (this.parameters != null) {
			for (Parameter parameter : this.parameters) {
				parameter.sem(tbl);
			}
		}
		for (Definition definition : this.local_defs) {
			if (definition instanceof IdDef) {
				definition.sem(tbl);
			}
			else if (definition instanceof Function) {
				Function def = (Function) definition;
				if (def.parameters != null) {
					Deque<SymbolEntry> params = new LinkedList<>();
					for (Parameter parameter : def.parameters) {
						params.add(new SymbolEntry(parameter.getName() , parameter.getType()));
					}
					tbl.addFuncEntry(def.name , def.return_type , params);
				}
				else {
					tbl.addFuncEntry(def.name , def.return_type , null);
				}
				tbl.openScope(def.name);
				def.sem(tbl);
				tbl.closeScope(def.name);
				
			}
			else {
				throw new IllegalArgumentException("You passed a non-handled definition");
			}
		}
		//		for (Statement statement : this.statements) {
		//			statement.sem(tbl);
		//		}
	}
	
	public String getName() {
		return name;
	}
}