package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;

public class Id extends Expression {
	private String name;
	
	public Id(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "id(" + this.name + ")";
	}
}