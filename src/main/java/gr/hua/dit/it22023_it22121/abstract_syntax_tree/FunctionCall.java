package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

import java.util.Deque;

public class FunctionCall extends Statement {
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
		return "";
	}
}