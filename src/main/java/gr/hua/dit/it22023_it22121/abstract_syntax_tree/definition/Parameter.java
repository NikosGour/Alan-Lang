package gr.hua.dit.it22023_it22121.abstract_syntax_tree.definition;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Definition;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

public class Parameter extends Definition {
	private String  name;
	private Type    type;
	private Boolean is_refrence;
	
	public Parameter(String name , Type type , Boolean is_refrence) {
		this.name        = name;
		this.type        = type;
		this.is_refrence = is_refrence;
	}
	
	@Override
	public String toString() {
		return "Param(" + this.name + ":" + this.type + ", refrence:" + (this.is_refrence ? "yes" : "no") + ")";
	}
}