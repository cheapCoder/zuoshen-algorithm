package A3medium.class08;

// 在数据加密和数据压缩中常需要对特殊的字符串进行编码。
// 给定的字母表A由26个小写英文字母组成，即 A={a, b...z}。
// 该字母表产生的长序字符串是指定字符串中字母从左到右出现的次序与字母在字母表中出现的次序相同，且每个字符最多出现1次。
// 例如，a，b，ab，bc，xyz等字符串是升序字符串。
// 对字母表A产生的所有长度不超过6的升序字符串按照字典排列编码如下:a(1)，b(2)，c(3)......，z(26)，ab(27)， ac(28)......
// 对于任意长度不超过16的升序字符串，迅速计算出它在上述字典中的编码。
// 输入描述: 第1行是一个正整数N，表示接下来共有N行，在接下来的N行中，每行给出一个字符串。
// 输出描述: 输出N行，每行对应于一个字符串编码。
// 示例1:
// 输入
// 3
// a
// b
// ab
// 输出
// 1
// 2
// 27
public class C03_StringToKth {

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
