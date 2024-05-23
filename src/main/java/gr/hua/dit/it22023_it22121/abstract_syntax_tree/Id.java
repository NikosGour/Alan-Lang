package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

public class Id extends Expression {
	private String name;
	
	public Id(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Id(" + this.name + ")";
	}
}