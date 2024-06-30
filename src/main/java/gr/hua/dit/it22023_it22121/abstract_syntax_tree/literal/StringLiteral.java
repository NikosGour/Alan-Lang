package gr.hua.dit.it22023_it22121.abstract_syntax_tree.literal;

import gr.hua.dit.it22023_it22121.abstract_syntax_tree.abstraction.Expression;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.definition.Function;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.symbol.SymbolTable;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.ArrayType;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.BasicType;
import gr.hua.dit.it22023_it22121.abstract_syntax_tree.type.Type;

import java.util.List;

public class StringLiteral extends Expression {
	private String value;
	
	public StringLiteral(String value) {
		this.value = value;
	}
	
	@Override
	public String toString(int depth) {
		return "\"" + this.value + "\"";
	}
	
	@Override
	public void sem(SymbolTable tbl) {
	
	}
	
	@Override
	public void gen(StringBuilder sb , int depth , SymbolTable tbl) {
		sb.append("\"" + this.value + "\"");
	}
	
	@Override
	public Type getType(SymbolTable tbl) {
		return new ArrayType(BasicType.Byte);
	}
}