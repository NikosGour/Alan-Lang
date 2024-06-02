package gr.hua.dit.it22023_it22121.abstract_syntax_tree.type;

public class ArrayType extends Type {
	private BasicType element_type;
	private Integer   size;
	
	public ArrayType(BasicType element_type , Integer size) {
		this.element_type = element_type;
		this.size         = size;
	}
	
	public ArrayType(BasicType element_type) {
		this.element_type = element_type;
	}
	
	@Override
	public String toString() {
		if (size != null) {
			return this.element_type + "[" + this.size + "]";
		}
		return this.element_type + "[" + "]";
	}
	
	public Type getType() {
		return this.element_type;
	}
}