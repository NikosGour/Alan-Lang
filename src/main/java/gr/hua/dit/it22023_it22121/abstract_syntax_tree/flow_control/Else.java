package gr.hua.dit.it22023_it22121.abstract_syntax_tree.flow_control;

import gr.hua.dit.it22023_it22121.Utils;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.Return;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Condition;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Statement;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.definition.Function;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.definition.IdDef;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;

import java.util.Deque;
import java.util.List;
import java.util.StringJoiner;

public class Else extends If {
	
	private Deque<Statement> elses;
	private Statement        elses_then;
	
	public Else(Condition condition , Deque<Statement> thens , Deque<Statement> elses) {
		super(condition , thens);
		this.elses = elses;
	}
	
	public Else(Condition condition , Deque<Statement> thens , Statement elses_then) {
		super(condition , thens);
		this.elses_then = elses_then;
	}
	
	public Else(Condition condition , Statement then , Deque<Statement> elses) {
		super(condition , then);
		this.elses = elses;
	}
	
	public Else(Condition condition , Statement then , Statement elses_then) {
		super(condition , then);
		this.elses_then = elses_then;
	}
	
	@Override
	public String toString(int depth) {
		String if_s = super.toString(depth);
		if (elses != null) {
			StringJoiner else_sj = new StringJoiner("\n" , "Else{\n" , "\n" + Utils.indent(depth) + "}");
			for (Statement statement : elses) {
				else_sj.add(Utils.indent(depth + 1) + statement.toString(depth + 1));
			}
			return if_s + "\n" + Utils.indent(depth) + else_sj.toString();
		}
		else {
			StringJoiner elses_then_sj = new StringJoiner("\n" , "Else{\n" , "\n" + Utils.indent(depth) + "}");
			elses_then_sj.add(Utils.indent(depth + 1) + elses_then.toString(depth + 1));
			return if_s + "\n" + Utils.indent(depth) + elses_then_sj.toString();
		}
	}
	
	@Override
	public void gen(StringBuilder sb , int depth , SymbolTable tbl) {
		super.gen(sb , depth , tbl);
		if (elses != null) {
			sb.append(Utils.indent(depth) + "else{\n");
			for (Statement statement : elses) {
				sb.append(Utils.indent(depth + 1));
				statement.gen(sb , depth + 1 , tbl);
				if (statement instanceof IdDef || statement instanceof If || statement instanceof While || statement instanceof Return) {
				}
				else {
					sb.append(";\n");
				}
			}
			sb.append(Utils.indent(depth) + "}\n");
		}
		else {
			sb.append(Utils.indent(depth) + "else ");
			elses_then.gen(sb , depth + 1 , tbl);
			if (elses_then instanceof IdDef || elses_then instanceof If || elses_then instanceof While || elses_then instanceof Return) {
			}
			else {
				sb.append(";\n");
			}
		}
	}
}