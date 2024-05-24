package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

public class BasicType extends Type {
	private enum BasicTypeEnum {
		INT, BYTE
	}
	
	;
	
	static public BasicType Int  = new BasicType(BasicTypeEnum.INT);
	static public BasicType Byte = new BasicType(BasicTypeEnum.BYTE);
	
	private BasicTypeEnum type;
	
	public BasicType(BasicTypeEnum type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return this.type.toString();
	}
}