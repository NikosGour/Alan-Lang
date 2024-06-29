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

public class While extends Statement {
	private Condition        condition;
	private Deque<Statement> thens;
	private Statement        then;
	
	public While(Condition condition , Deque<Statement> thens) {
		this.condition = condition;
		this.thens     = thens;
	}
	
	public While(Condition condition , Statement then) {
		this.condition = condition;
		this.then      = then;
	}
	
	@Override
	public String toString(int depth) {
		if (thens != null) {
			StringJoiner then_sj;
			if (thens.size() > 1) {
				then_sj = new StringJoiner("\n" , "{\n" , "\n" + Utils.indent(depth) + "}");
			}
			else {
				then_sj = new StringJoiner("\n" , "{" , "}");
			}
			for (Statement statement : thens) {
				then_sj.add(Utils.indent(depth + 1) + statement.toString(depth + 1));
			}
			return "While(" + condition.toString(depth) + ")" + then_sj.toString();
		}
		else {
			return "While(" + condition.toString(depth) + ")" + "{" + then.toString(depth) + "}";
		}
	}
	
	@Override
	public void sem(SymbolTable tbl) {
		
		this.condition.sem(tbl);
		if (! this.condition.getType(tbl).equals(BasicType.Byte)) {
			throw new RuntimeException("Type mismatch in while condition, expected boolean, got `" +
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
			this.then.sem(tbl);
		}
	}
	
	@Override
	public void gen(StringBuilder sb , int depth) {
		sb.append("while (");
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
			sb.append(Utils.indent(depth) + "}\n");
		}
		else {
			sb.append(" ");
			this.then.gen(sb , depth + 1);
			sb.append(";\n");
		}
		
	}
}