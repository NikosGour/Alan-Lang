package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

public class ArrayType extends Type {
	private BasicType element_type;
	
	public ArrayType(BasicType element_type) {
		this.element_type = element_type;
	}
}