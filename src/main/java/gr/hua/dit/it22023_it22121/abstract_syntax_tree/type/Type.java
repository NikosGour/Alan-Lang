package gr.hua.dit.it22023_it22121.abstract_syntax_tree.type;

public class Type {
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
			return false;
		}
		
		if (obj == this) {
			return true;
		}
		
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		
		Type other = (Type) obj;
		return this.toString().equals(other.toString());
	}
}