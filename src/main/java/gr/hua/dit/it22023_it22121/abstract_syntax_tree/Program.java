package gr.hua.dit.it22023_it22121.abstract_syntax_tree;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Statement;

import java.util.List;
import java.util.StringJoiner;

public class Program {
	private List<Statement> statements;
	
	public Program(List<Statement> statements) {
		this.statements = statements;
	}
	
	@Override
	public String toString() {
		
		StringJoiner sj = new StringJoiner("|" , "Program(" , ")");
		for (Statement statement : this.statements) {
			sj.add(statement.toString());
		}
		return sj.toString();
	}
}