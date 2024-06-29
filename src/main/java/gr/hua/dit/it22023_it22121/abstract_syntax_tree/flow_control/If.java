package gr.hua.dit.it22023_it22121.abstract_syntax_tree.flow_control;

import gr.hua.dit.it22023_it22121.Utils;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.Return;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Condition;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Statement;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.definition.IdDef;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.BasicType;

import java.util.Deque;
import java.util.StringJoiner;

public class If extends Statement {
	protected Condition        condition;
	protected Deque<Statement> thens;
	protected Statement        then;
	
	public If(Condition condition , Deque<Statement> thens) {
		this.condition = condition;
		this.thens     = thens;
	}
	
	public If(Condition condition , Statement then) {
		this.condition = condition;
		this.then      = then;
	}
	
	@Override
	public String toString(int depth) {
		if (thens != null) {
			
			StringJoiner then_sj;
			if (! thens.isEmpty()) {
				then_sj = new StringJoiner("\n" , "{\n" , "\n" + Utils.indent(depth) + "}");
			}
			else {
				then_sj = new StringJoiner("\n" , "{" , "}");
			}
			for (Statement statement : thens) {
				then_sj.add(Utils.indent(depth + 1) + statement.toString(depth + 1));
			}
			return "If(" + condition.toString(depth) + ")" + then_sj.toString();
		}
		else {
			StringJoiner then_sj = new StringJoiner("\n" , "{\n" , "\n" + Utils.indent(depth) + "}");
			then_sj.add(Utils.indent(depth + 1) + then.toString(depth + 1));
			return "If(" + condition.toString(depth) + ")" + then_sj.toString();
			
		}
		
	}
	
	@Override
	public void sem(SymbolTable tbl) {
		this.condition.sem(tbl);
		if (! this.condition.getType(tbl).equals(BasicType.Byte)) {
			throw new RuntimeException("Type mismatch in if condition, expected boolean, got `" +
			                           this.condition.toString(0) +
			                           "`: " +
			                           this.condition.getType(tbl));
		}
		if (thens != null) {
			for (Statement statement : thens) {
				statement.sem(tbl);
			}
		}
		else {
			then.sem(tbl);
		}
		
	}
	
	@Override
	public void gen(StringBuilder sb , int depth) {
		sb.append("if (");
		this.condition.gen(sb , depth);
		sb.append(") ");
		if (thens != null) {
			sb.append("{\n");
			for (Statement statement : thens) {
				sb.append(Utils.indent(depth + 1));
				statement.gen(sb , depth + 1);
				if (statement instanceof IdDef || statement instanceof If || statement instanceof While || statement instanceof Return) {
				}
				else {
					sb.append(";\n");
				}
			}
			sb.append(Utils.indent(depth));
			sb.append("}\n");
		}
		else {
			sb.append(" ");
			then.gen(sb , depth + 1);
			if (then instanceof IdDef || then instanceof If || then instanceof While || then instanceof Return) {
			}
			else {
				sb.append(";\n");
			}
		}
	}
}