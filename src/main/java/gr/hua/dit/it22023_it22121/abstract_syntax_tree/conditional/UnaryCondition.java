package gr.hua.dit.it22023_it22121.abstract_syntax_tree.conditional;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Condition;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.BasicType;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

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
	public String toString(int depth) {
		return operand.toString() + " " + e.toString(depth);
	}
	
	@Override
	public void sem(SymbolTable tbl) {
		this.e.sem(tbl);
		if (! e.getType(tbl).equals(BasicType.Byte)) {
			throw new RuntimeException(
					"Type mismatch in unary condition, expected boolean, got `" + e.toString(0) + "`: " + e.getType(tbl));
		}
		
	}
	
	@Override
	public Type getType(SymbolTable tbl) {
		return BasicType.Byte;
	}
}