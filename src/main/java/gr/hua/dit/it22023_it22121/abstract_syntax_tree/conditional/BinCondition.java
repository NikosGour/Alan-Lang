package gr.hua.dit.it22023_it22121.abstract_syntax_tree.conditional;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Condition;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.definition.Function;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.BasicType;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

import java.util.List;

public class BinCondition extends Condition {
	private Expression    l;
	private Expression    r;
	private ConditionEnum operand;
	
	
	private enum ConditionEnum {
		DOUBLE_EQUAL("=="), NOT_EQUAL("!="), LESS("<"), GREATER(">"), LESS_OR_EQUAL("<="), GREATER_OR_EQUAL(">="), AND("&"), OR("|");
		
		private final String s;
		
		ConditionEnum(final String s) {
			this.s = s;
		}
		
		public String toString() {
			return s;
		}
		
	}
	
	public static ConditionEnum DOUBLE_EQUAL     = ConditionEnum.DOUBLE_EQUAL;
	public static ConditionEnum NOT_EQUAL        = ConditionEnum.NOT_EQUAL;
	public static ConditionEnum LESS             = ConditionEnum.LESS;
	public static ConditionEnum GREATER          = ConditionEnum.GREATER;
	public static ConditionEnum LESS_OR_EQUAL    = ConditionEnum.LESS_OR_EQUAL;
	public static ConditionEnum GREATER_OR_EQUAL = ConditionEnum.GREATER_OR_EQUAL;
	public static ConditionEnum AND              = ConditionEnum.AND;
	public static ConditionEnum OR               = ConditionEnum.OR;
	
	
	public BinCondition(Expression l , Expression r , ConditionEnum operand) {
		this.l       = l;
		this.r       = r;
		this.operand = operand;
	}
	
	@Override
	public String toString(int depth) {
		return l.toString(depth) + " " + operand.toString() + " " + r.toString(depth);
	}
	
	@Override
	public void sem(SymbolTable tbl) {
		this.l.sem(tbl);
		this.r.sem(tbl);
		if (! l.getType(tbl).equals(r.getType(tbl))) {
			throw new RuntimeException("Type mismatch in binary condition, left `" +
			                           l.toString(0) +
			                           "`: " +
			                           l.getType(tbl) +
			                           " and right `" +
			                           r.toString(0) +
			                           "`: " +
			                           r.getType(tbl));
			
		}
		
	}
	
	@Override
	public void gen(StringBuilder sb , int depth , SymbolTable tbl){
		
		this.l.gen(sb , depth , tbl);
		if (this.operand == ConditionEnum.AND) {
			sb.append(" && ");
		}
		else if (this.operand == ConditionEnum.OR) {
			sb.append(" || ");
		}
		else {
			sb.append(" " + this.operand.toString() + " ");
		}
		this.r.gen(sb , depth , tbl);
	}
	
	@Override
	public Type getType(SymbolTable tbl) {
		return BasicType.Byte;
	}
}