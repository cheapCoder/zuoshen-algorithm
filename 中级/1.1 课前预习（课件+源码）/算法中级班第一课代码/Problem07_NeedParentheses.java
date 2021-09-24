package class01;

public class Problem07_NeedParentheses {

	public static int needParentheses(String str) {
		int leftRest = 0;
		int needSolveRight = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				leftRest++;
			} else {
				if (leftRest == 0) {
					needSolveRight++;
				} else {
					leftRest--;
				}
			}
		}
		return leftRest + needSolveRight;
	}

	public static void main(String args[]) {

	}

}
