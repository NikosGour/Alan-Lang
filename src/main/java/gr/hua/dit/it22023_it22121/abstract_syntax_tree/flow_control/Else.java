package gr.hua.dit.it22023_it22121.abstract_syntax_tree.flow_control;

import gr.hua.dit.it22023_it22121.Utils;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Condition;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Statement;

import java.util.Deque;
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
	public void gen(StringBuilder sb , int depth) {
		super.gen(sb , depth);
		if (elses != null) {
			for (Statement statement : elses) {
				statement.gen(sb , depth + 1);
			}
		}
		else {
			elses_then.gen(sb , depth + 1);
		}
	}
}