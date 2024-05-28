package gr.hua.dit.it22023_it22121;

public class Utils {
	public static String repeat(String str , int times) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < times; i++) {
			sb.append(str);
		}
		return sb.toString();
	}
	
	public static String indent(int depth) {
		return repeat(" " , depth * 4);
	}
}