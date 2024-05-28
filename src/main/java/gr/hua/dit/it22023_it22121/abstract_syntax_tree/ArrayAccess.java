package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;

public class ArrayAccess extends Expression {
	private String     name;
	private Expression index;
	
	public ArrayAccess(String name , Expression index) {
		this.name  = name;
		this.index = index;
	}
	
	@Override
	public String toString() {
		return this.name + "[" + this.index + "]";
	}
	
	public String getName() {
		return name;
	}
	
	public Expression getIndex() {
		return index;
	}
}