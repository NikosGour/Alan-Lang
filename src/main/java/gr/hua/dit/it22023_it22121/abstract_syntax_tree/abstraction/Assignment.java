package gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.ArrayAccess;

public class Assignment extends Statement {
	private Expression id;
	private Expression e;
	
	public Assignment(Expression id , Expression e) {
		this.id = id;
		this.e  = e;
	}
	
	@Override
	public String toString(int depth) {
		if (id instanceof ArrayAccess) {
			ArrayAccess a = (ArrayAccess) id;
			return a.getName() + "[" + a.getIndex() + "] = " + e.toString();
			
		}
		return "Assignment(" + id.toString(depth) + " = " + e.toString(depth) + ")";
	}
}