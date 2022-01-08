package A4high.class03;

import java.util.HashMap;
import java.util.LinkedList;

// 给定一个字符串str，str表示一个公式，公式里可能有整数、加减乘除符号和左右括号，返回公式的计算结果。
// 【举例】
// str="48*((70-65)-43)+8*1"，返回-1816。
// str="3+1*4"，返回7。
// str="3+(1*4)"，返回7。
// 【说明】 
// 1. 可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查。
// 2. 如果是负数，就需要用括号括起来，比如"4*(-3)"。
// 但如果负数作为公式的开头 或括号部分的开头，则可以没有括号，比如"-3*4"和"(-3*4)"都是合法的。3. 不用考虑计算过程中会发生溢出的情况

public class C03_ExpressionCompute {

	// public static int getValue(String str) {
	// return value(str.toCharArray(), 0)[0];
	// }

	// public static int[] value(char[] str, int i) {
	// LinkedList<String> que = new LinkedList<String>();
	// int pre = 0;
	// int[] bra = null;
	// while (i < str.length && str[i] != ')') {
	// if (str[i] >= '0' && str[i] <= '9') {
	// pre = pre * 10 + str[i++] - '0';
	// } else if (str[i] != '(') {
	// addNum(que, pre);
	// que.addLast(String.valueOf(str[i++]));
	// pre = 0;
	// } else {
	// bra = value(str, i + 1);
	// pre = bra[0];
	// i = bra[1] + 1;
	// }
	// }
	// addNum(que, pre);
	// return new int[] { getNum(que), i };
	// }

	// public static void addNum(LinkedList<String> que, int num) {
	// if (!que.isEmpty()) {
	// int cur = 0;
	// String top = que.pollLast();
	// if (top.equals("+") || top.equals("-")) {
	// que.addLast(top);
	// } else {
	// cur = Integer.valueOf(que.pollLast());
	// num = top.equals("*") ? (cur * num) : (cur / num);
	// }
	// }
	// que.addLast(String.valueOf(num));
	// }

	// public static int getNum(LinkedList<String> que) {
	// int res = 0;
	// boolean add = true;
	// String cur = null;
	// int num = 0;
	// while (!que.isEmpty()) {
	// cur = que.pollFirst();
	// if (cur.equals("+")) {
	// add = true;
	// } else if (cur.equals("-")) {
	// add = false;
	// } else {
	// num = Integer.valueOf(cur);
	// res += add ? num : (-num);
	// }
	// }
	// return res;
	// }

	public static int getValue(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		char[] arr = str.toCharArray();

		// 双指针获取所有左右括号的索引map
		HashMap<Integer, Integer> bracketMap = new HashMap<>();
		int left = 0;
		int right = arr.length - 1;
		for (; left < arr.length; left++) {
			if (arr[left] == '(') {
				while (arr[right] != ')') {
					right--;
					if (right <= left) {
						System.out.println("左右括号不能匹配！");
						return 0;
					}
				}
				bracketMap.put(left, right);
			}
		}

		LinkedList<Integer> numStack = new LinkedList<>();
		LinkedList<Character> operateStack = new LinkedList<>();
		// 添加哨兵0，这样就不用区分第一个数字可能为负数的情况了
		numStack.addLast(0);
		if (arr[0] != '-') {
			operateStack.addLast('+');
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '(') {
				int rightBracket = bracketMap.get(i);
				// System.out.println(str.substring(i + 1, rightBracket));
				int num = getValue(str.substring(i + 1, rightBracket));
				// 操作符栈顶为乘除时，优先计算
				char lastOperate = operateStack.peekLast();
				if (lastOperate == '*' || lastOperate == '/') {
					operateStack.pollLast();
					num = lastOperate == '*' ? numStack.pollLast() * num : numStack.pollLast() / num;
				}

				numStack.addLast(num);
				i = rightBracket;
			} else if (arr[i] == '+' || arr[i] == '-' || arr[i] == '*' || arr[i] == '/') {
				operateStack.addLast(arr[i]);
			} else if (arr[i] >= '0' && arr[i] <= '9') { // 保留数字
				int num = 0;
				// 往后继续查找数字
				while (i < arr.length && arr[i] >= '0' && arr[i] <= '9') {
					num = num * 10 + (arr[i] - '0');
					i++;
				}
				i--;

				// 操作符栈顶为乘除时，优先计算
				char lastOperate = operateStack.peekLast();
				if (lastOperate == '*' || lastOperate == '/') {
					operateStack.pollLast();
					num = lastOperate == '*' ? numStack.pollLast() * num : numStack.pollLast() / num;
				}

				numStack.addLast(num);
			} else {
				System.out.println("不合法");
				return 0;
			}
		}

		int res = numStack.pollFirst();
		while (!numStack.isEmpty()) {
			char operate = operateStack.pollFirst();
			int num = numStack.pollFirst();
			res = operate == '+' ? res + num : res - num;
		}

		return res;
	}

	public static void main(String[] args) {
		String exp = "48*((70-65)-43)+8*1";
		System.out.println(getValue(exp));

		exp = "4*(6+78)+53-9/2+45*8";
		System.out.println(getValue(exp));

		exp = "10-5*3";
		System.out.println(getValue(exp));

		exp = "-3*4";
		System.out.println(getValue(exp));

		exp = "3+1*4";
		System.out.println(getValue(exp));

	}

}
