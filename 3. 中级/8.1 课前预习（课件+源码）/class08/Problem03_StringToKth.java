package class08;

public class Problem03_StringToKth {

	// 第i个字符开头、长度为len的所有字符串中的第一个字符串，是第几个
	public static int f(int i, int len) {
		int sum = 0;
		if (len == 1) {
			return 1;
		}
		for (int j = i + 1; j <= 26; j++) {
			sum += f(j, len - 1);
		}
		return sum;

	}

	// 长度为len的字符串有多少个
	public static int g(int len) {
		int sum = 0;
		for (int i = 1; i <= 26; i++) {
			sum += f(i, len);
		}
		return sum;
	}

	public static int kth(String s) {
		char[] str = s.toCharArray();
		int sum = 0;
		int len = str.length;
		for (int i = 1; i < len; i++) {
			sum += g(i);
		}
		int first = str[0] - 'a' + 1;
		for (int i = 1; i < first; i++) {
			sum += f(i, len);
		}
		int pre = first;
		for (int i = 1; i < len; i++) {
			int cur = str[i] - 'a' + 1;
			for (int j = pre + 1; j < cur; j++) {
				sum += f(j, len - i);
			}
			pre = cur;
		}
		return sum + 1;
	}

	public static void main(String[] args) {
		String test = "bc";
		System.out.println(kth(test));
	}

}
