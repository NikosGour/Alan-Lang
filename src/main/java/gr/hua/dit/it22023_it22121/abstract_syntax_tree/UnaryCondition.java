package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

public class UnaryCondition extends Condition {
	private Expression    e;
	private ConditionEnum operand;
	
	private enum ConditionEnum {
		NOT("!");
		
		private final String s;
		
		ConditionEnum(final String s) {
			this.s = s;
		}
		
		public String toString() {
			return s;
		}
		
	}
	
	public static ConditionEnum NOT = ConditionEnum.NOT;
	
	public UnaryCondition(Expression e , ConditionEnum operand) {
		this.e       = e;
		this.operand = operand;
	}
	
	@Override
	public String toString() {
		return operand.toString() + " " + e.toString();
	}
}