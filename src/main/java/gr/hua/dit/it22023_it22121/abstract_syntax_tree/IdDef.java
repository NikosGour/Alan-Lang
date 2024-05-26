package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

public class IdDef extends Definition {
	private String name;
	private Type   type;
	
	public IdDef(String name , Type type) {
		this.name = name;
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "IdDef(" + this.name + ":" + this.type + ")";
	}
}