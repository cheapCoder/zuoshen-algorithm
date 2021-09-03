package skill;

// 一个完整的括号字符串定义规则如下:
// 1空字符串是完整的。
// 2如果s是完整的字符串，那么(s)也是完整的。 
// 3如果s和t是完整的字符串，将它们连接起来形成的st也是完整的。 
// 例如，"(()())", ""和"(())()"是完整的括号字符串，"())(", "()(" 和 ")" 是不完整的括号字符串。 
// 牛牛有一个括号字符串s,现在需要在其中任意位置尽量少地添加括号,将其转化 为一个完整的括号字符串。
// 请问牛牛至少需要添加多少个括号。
public class C08_NeedParentheses {

	// public static int needParentheses(String str) {
	// int leftRest = 0;
	// int needSolveRight = 0;
	// for (int i = 0; i < str.length(); i++) {
	// if (str.charAt(i) == '(') {
	// leftRest++;
	// } else {
	// if (leftRest == 0) {
	// needSolveRight++;
	// } else {
	// leftRest--;
	// }
	// }
	// }
	// return leftRest + needSolveRight;
	// }

	public static int needParentheses(String str) {
		int makeUp = 0;
		int last = 0;
		char[] arr = str.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '(') {
				last++;
			} else if (arr[i] == ')') {
				last--;
			}

			if (last < 0) {
				makeUp++;
				last++;
			}
		}

		return last + makeUp;
	}

	public static void main(String args[]) {

	}

}
