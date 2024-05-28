package gr.hua.dit.it22023_it22121.abstract_syntax_tree.flow_control;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Condition;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Statement;

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
	public String toString() {
		if (thens != null) {
			
			StringJoiner then_sj = new StringJoiner("\n" , "{" , "}");
			for (Statement statement : thens) {
				then_sj.add(statement.toString());
			}
			return "If(" + condition.toString() + ")" + then_sj.toString();
		}
		else {
			return "If(" + condition.toString() + ")" + "{" + then.toString() + "}";
		}
		
	}
}