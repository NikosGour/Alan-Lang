package gr.hua.dit.it22023_it22121.abstract_syntax_tree.flow_control;

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
	public String toString() {
		String if_s = super.toString();
		if (elses != null) {
			StringJoiner else_sj = new StringJoiner("\n" , "Else{" , "}");
			for (Statement statement : elses) {
				else_sj.add(statement.toString());
			}
			return if_s + else_sj.toString();
		}
		else {
			return if_s + "Else{" + elses_then.toString() + "}";
		}
	}
}