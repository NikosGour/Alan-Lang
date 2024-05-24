package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

import java.util.Deque;
import java.util.List;

public class Function extends Definition {
	private String           name;
	private Deque<Parameter> parameters;
	
	public Function(String name) {
		this.name = name;
	}
	
	public Function(String name , Deque<Parameter> parameters) {
		this.name       = name;
		this.parameters = parameters;
	}
	
	@Override
	public String toString() {
		return "Func(" + this.name + ")";
	}
}