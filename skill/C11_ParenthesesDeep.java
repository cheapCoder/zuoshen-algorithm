package skill;

// 一个合法的括号匹配序列有以下定义:
// 1空串""是一个合法的括号匹配序列 
// 2如果"X"和"Y"都是合法的括号匹配序列,"XY"也是一个合法的括号匹配序列
// 3如果"X"是一个合法的括号匹配序列,那么"(X)"也是一个合法的括号匹配序列 
// 4每个合法的括号序列都可以由以上规则生成。
// 例如: "","()","()()","((()))"都是合法的括号序列 
// 对于一个合法的括号序列我们又有以下定义它的深度:
// 1空串""的深度是0 
// 2如果字符串"X"的深度是x,字符串"Y"的深度是y,那么字符串"XY"的深度为 max(x,y) 3、如果"X"的深度是x,那么字符串"(X)"的深度是x+1
// 例如: "()()()"的深度是1,"((()))"的深度是3。
// q1：现在给你一个合法的括号序列,需要你计算出其深度。

// q2拓展题：找到最长的合法子序列
public class C11_ParenthesesDeep {

	// public static boolean isValid(char[] str) {
	// if (str == null || str.equals("")) {
	// return false;
	// }
	// int status = 0;
	// for (int i = 0; i < str.length; i++) {
	// if (str[i] != ')' && str[i] != '(') {
	// return false;
	// }
	// if (str[i] == ')' && --status < 0) {
	// return false;
	// }
	// if (str[i] == '(') {
	// status++;
	// }
	// }
	// return status == 0;
	// }

	// public static int deep(String s) {
	// char[] str = s.toCharArray();
	// if (!isValid(str)) {
	// return 0;
	// }
	// int count = 0;
	// int max = 0;
	// for (int i = 0; i < str.length; i++) {
	// if (str[i] == '(') {
	// max = Math.max(max, ++count);
	// } else {
	// count--;
	// }
	// }
	// return max;
	// }

	// 拓展题：找到最长的合法子序列
	// public static int maxLength(String str) {
	// if (str == null || str.equals("")) {
	// return 0;
	// }
	// char[] chas = str.toCharArray();
	// int[] dp = new int[chas.length];
	// int pre = 0;
	// int res = 0;
	// for (int i = 1; i < chas.length; i++) {
	// if (chas[i] == ')') {
	// pre = i - dp[i - 1] - 1;
	// if (pre >= 0 && chas[pre] == '(') {
	// dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
	// }
	// }
	// res = Math.max(res, dp[i]);
	// }
	// return res;
	// }

	// 左神做法：对整个字符串所有位置，求必须以当前位置结尾的字符串最长合法长度为多少;
	// 两种情况：
	// 1. 当前字符为'('，则结果为0;
	// 2. 当前字符为')'，则看前一字符的最长合法串长度的前一位是后为'('
	// 注意索引溢出的判断
	public static int maxLength(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}

		int res = 0;
		int[] cache = new int[str.length()];
		cache[0] = 0;
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i) == ')') {
				int j = i - cache[i - 1] - 1;
				if (j >= 0 && str.charAt(j) == '(') {
					cache[i] = cache[i - 1] + 2 + (j - 1 >= 0 ? cache[j - 1] : 0);
				}
			}
			res = Math.max(res, cache[i]);
		}
		return res;
	}

	// 拓展题
	// 自己的想法：两个变量表示左右边界，一个变量表示当前找到的最长子序列，用count表示栈，
	// count==0就对比左右边界是否是更大的子序列，count<0就把左边界右移到右边界
	public static int maxLength2(String str) {
		// if (str == null || str.length() == 0) {
		// return 0;
		// }
		int leftBorder = 0;
		int rightBorder = 0;
		int count = 0;
		int maxLen = 0;
		while (rightBorder < str.length()) {
			if (str.charAt(rightBorder) == '(') {
				count++;
			} else if (str.charAt(rightBorder) == ')') {
				count--;
				if (count == 0) {
					maxLen = Math.max(maxLen, rightBorder - leftBorder + 1);
					leftBorder = rightBorder + 1;
				} else if (count < 0) { // 序列不合法
					return -1;
				}
			}

			rightBorder++;
		}

		return maxLen;
	}

	public static void main(String[] args) {
		String test = "((()()()((()))()))";
		// System.out.println(deep(test));
		System.out.println(maxLength(test));
		System.out.println(maxLength2(test));

	}

}
