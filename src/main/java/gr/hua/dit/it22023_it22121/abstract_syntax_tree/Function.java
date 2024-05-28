package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

import java.util.Deque;
import java.util.StringJoiner;


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
		this.name        = name;
		this.return_type = return_type;
		this.statements  = statements;
		this.local_defs  = local_defs;
		this.parameters  = parameters;
	}
	
	@Override
	public String toString() {
		
		StringJoiner statements_sj = new StringJoiner("\n" , "{" , "}");
		for (Statement statement : this.statements) {
			statements_sj.add(statement.toString());
		}
		StringJoiner local_defs_sj = new StringJoiner("\n" , "local_defs{" , "}");
		for (Statement local_def : this.local_defs) {
			local_defs_sj.add(local_def.toString());
		}
		StringJoiner params_sj = new StringJoiner("\n" , "params{" , "}");
		if (this.parameters != null) {
			for (Statement param : this.parameters) {
				params_sj.add(param.toString());
			}
		}
		return "Func(" +
		       this.name +
		       ":" +
		       this.return_type +
		       ")" +
		       params_sj.toString() +
		       local_defs_sj.toString() +
		       statements_sj.toString();
	}
}