package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;

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
	public String toString() {
		if (call_params == null) {
			return "call(" + this.name + ")";
		}
		
		StringJoiner sj = new StringJoiner("," , "{" , "}");
		for (Expression param : this.call_params) {
			sj.add(param.toString());
		}
		return "call(" + this.name + ")" + sj.toString();
	}
}