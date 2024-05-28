package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

public class CharLiteral extends Expression {
	private String value;
	
	public CharLiteral(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "'" + this.value + "'";
	}
}